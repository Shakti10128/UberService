package org.shakti.uberbookingservice.Services;

import org.shakti.uberbookingservice.Adaptors.BookingAdaptor;
import org.shakti.uberbookingservice.Apis.LocationServiceApi;
import org.shakti.uberbookingservice.Dtos.*;
import org.shakti.uberbookingservice.Exceptions.CustomError;
import org.shakti.uberbookingservice.Repositories.BookingRepository;
import org.shakti.uberbookingservice.Repositories.DriverRepository;
import org.shakti.uberbookingservice.Repositories.PassengerRepository;
import org.shakti.ubercommonlibraries.Models.Booking;
import org.shakti.ubercommonlibraries.Models.Driver;
import org.shakti.ubercommonlibraries.Models.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final String LOCATION_SERVICE_URI = "http://localhost:8082";

    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;
    private final BookingAdaptor bookingAdaptor;
    private final LocationServiceApi locationServiceApi;

    public BookingServiceImpl(BookingRepository bookingRepository, PassengerRepository passengerRepository,
                              BookingAdaptor bookingAdaptor,LocationServiceApi locationServiceApi,
                              DriverRepository driverRepository) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.driverRepository = driverRepository;
        this.bookingAdaptor = bookingAdaptor;
        this.locationServiceApi = locationServiceApi;
    }


    @Override
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto bookingDetails) {
        try{
            // first check either the passenger exist or not
            Passenger passenger = passengerRepository.findById(bookingDetails.getPassengerId())
                    .orElseThrow(() ->
                            new CustomError("Passenger not found with id: " + bookingDetails.getPassengerId(),HttpStatus.BAD_REQUEST));

            // create the brand-new booking object
            Booking booking = bookingAdaptor.createBookingRequestToBooking(bookingDetails, passenger);

            Booking newBooking = bookingRepository.save(booking);

            NearbyDriversRequestDto nearbyDriversRequestDto = NearbyDriversRequestDto.builder()
                    .latitude(bookingDetails.getStartLocation().getLatitude())
                    .longitude(bookingDetails.getStartLocation().getLongitude())
                    .searchRadius(10) // default radius for searching the nearby driver
                    .build();

            processNearbyDriversAsync(nearbyDriversRequestDto);

            // make api call to location service to get the nearby drivers using restTemplate or retrofit
//            ResponseEntity<DriverLocationDto[]> result = restTemplate.postForEntity(LOCATION_SERVICE_URI+"/api/v1/location/drivers/nearby", nearbyDriversRequestDto ,DriverLocationDto[].class);
//
//            // if the api call is being successful to fetch the nearby driver
//            if(result.getStatusCode() == HttpStatus.OK && result.getBody() != null){
//                // list of all nearby driver
//                List<DriverLocationDto> driverLocations = Arrays.asList(result.getBody());
//                driverLocations.forEach((driver)->{
//                    System.out.println(driver.getDriverId() + " " + driver.getLatitude() + " " + driver.getLongitude());
//                });
//            }

            return bookingAdaptor.bookingToCreateBookingResponse(newBooking);
        }
        catch (CustomError ce) {
            throw ce;
        } catch (Exception ex) {
            throw new CustomError("Internal server error occurred while creating booking", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AssignDriverResponseDto updateBookingStatusAndDriver(AssignDriverRequestDto requestDto, Long bookingId) {
        try{
            // if there is something missing from the required parameters
            if(requestDto.getBookingStatus() == null || requestDto.getDriverId() == null
               || bookingId == null){
                throw new CustomError("Missing bookingId or driverId or booking status",HttpStatus.BAD_REQUEST);
            }

            // check booking exist or not
            Booking existingBooking = bookingRepository.findBookingById(bookingId)
                    .orElseThrow(()-> new CustomError("Booking not found with id: " + bookingId,HttpStatus.BAD_REQUEST));

            // check driver exist or not
            Driver existingDriver = driverRepository.findById(requestDto.getDriverId()).orElseThrow(
                    ()-> new CustomError("Driver not found with id: " + requestDto.getDriverId(), HttpStatus.BAD_REQUEST)
            );

            // update the booking status and driver if everything is ok
            existingBooking.setBookingStatus(requestDto.getBookingStatus());
            existingBooking.setDriver(existingDriver);
            bookingRepository.save(existingBooking);

            return bookingAdaptor.bookingToAssignDriverResponseDto(existingBooking);
        }
        catch (CustomError ce) {
            throw ce;
        }
        catch (Exception ex) {
            throw new CustomError("Internal server error occurred while updating the booking status and driver", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void processNearbyDriversAsync(NearbyDriversRequestDto requestDto){
        Call<DriverLocationDto[]> call = locationServiceApi.nearbyDrivers(requestDto);

        // async callback function
        call.enqueue(new Callback<DriverLocationDto[]>() {

            // what should happen on response
            @Override
            public void onResponse(Call<DriverLocationDto[]> call, Response<DriverLocationDto[]> response) {
                if(response.isSuccessful() && response.body() != null){
                    // list of all nearby driver
                    List<DriverLocationDto> driverLocations = Arrays.asList(response.body());
                    driverLocations.forEach((driver)->{
                        System.out.println(driver.getDriverId() + " " + driver.getLatitude() + " " + driver.getLongitude());
                    });
                }
            }

            // what should happen on failure
            @Override
            public void onFailure(Call<DriverLocationDto[]> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
