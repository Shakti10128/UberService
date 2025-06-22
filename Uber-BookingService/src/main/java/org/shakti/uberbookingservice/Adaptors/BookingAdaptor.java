package org.shakti.uberbookingservice.Adaptors;

import org.shakti.uberbookingservice.Dtos.CreateBookingRequestDto;
import org.shakti.uberbookingservice.Dtos.CreateBookingResponseDto;
import org.shakti.ubercommonlibraries.Enums.BookingStatus;
import org.shakti.ubercommonlibraries.Models.Booking;
import org.shakti.ubercommonlibraries.Models.Passenger;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingAdaptor {
    public Booking toBooking(CreateBookingRequestDto createBookingRequestDto, Passenger passenger) {
        return Booking.builder()
                .bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                .startLocation(createBookingRequestDto.getStartLocation())
                .endLocation(createBookingRequestDto.getEndLocation())
                .passenger(passenger)
                .build();
    }

    public CreateBookingResponseDto toResponse(Booking booking) {
        return CreateBookingResponseDto.builder()
                .bookingId(booking.getId())
                .bookingStatus(booking.getBookingStatus().toString())
                .build();
    }
}
