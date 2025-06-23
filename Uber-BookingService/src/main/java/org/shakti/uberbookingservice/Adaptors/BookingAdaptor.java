package org.shakti.uberbookingservice.Adaptors;

import org.shakti.uberbookingservice.Dtos.*;
import org.shakti.ubercommonlibraries.Enums.BookingStatus;
import org.shakti.ubercommonlibraries.Models.Booking;
import org.shakti.ubercommonlibraries.Models.Driver;
import org.shakti.ubercommonlibraries.Models.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingAdaptor {
    public Booking createBookingRequestToBooking(CreateBookingRequestDto createBookingRequestDto, Passenger passenger) {
        return Booking.builder()
                .bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                .startLocation(createBookingRequestDto.getStartLocation())
                .endLocation(createBookingRequestDto.getEndLocation())
                .passenger(passenger)
                .build();
    }

    public CreateBookingResponseDto bookingToCreateBookingResponse(Booking booking) {
        return CreateBookingResponseDto.builder()
                .bookingId(booking.getId())
                .bookingStatus(booking.getBookingStatus().toString())
                .build();
    }


    public AssignDriverResponseDto bookingToAssignDriverResponseDto(Booking booking) {
        DriverDto driverDto = DriverDto.builder()
                .id(booking.getDriver().getId())
                .licenseNumber(booking.getDriver().getLicenseNumber())
                .phoneNumber(booking.getDriver().getPhoneNumber())
                .name(booking.getDriver().getName())
                .build();
        return AssignDriverResponseDto.builder()
                .bookingId(booking.getId())
                .bookingStatus(booking.getBookingStatus())
                .driver(driverDto)
                .build();
    }

    public NearbyDriversRequestDto convertToNearbyDriversRequestDto(Booking booking) {
        return NearbyDriversRequestDto.builder()
                .latitude(booking.getStartLocation().getLatitude())
                .longitude(booking.getStartLocation().getLongitude())
                .searchRadius(10) // default radius for searching the nearby driver
                .build();
    }

    public RideRequestDto convertToRideRequestDto(List<Long> driverList, Long passengerId) {
        return RideRequestDto.builder()
                .passengerId(passengerId)
                .driverIds(driverList)
                .build();
    }
}
