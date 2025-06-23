package org.shakti.uberbookingservice.Adaptors;

import org.shakti.uberbookingservice.Dtos.CreateBookingRequestDto;
import org.shakti.uberbookingservice.Dtos.CreateBookingResponseDto;
import org.shakti.uberbookingservice.Dtos.AssignDriverResponseDto;
import org.shakti.uberbookingservice.Dtos.DriverDto;
import org.shakti.ubercommonlibraries.Enums.BookingStatus;
import org.shakti.ubercommonlibraries.Models.Booking;
import org.shakti.ubercommonlibraries.Models.Passenger;
import org.springframework.stereotype.Component;

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
}
