package org.shakti.uberbookingservice.Services;

import org.shakti.uberbookingservice.Dtos.CreateBookingRequestDto;
import org.shakti.uberbookingservice.Dtos.CreateBookingResponseDto;
import org.shakti.uberbookingservice.Dtos.AssignDriverRequestDto;
import org.shakti.uberbookingservice.Dtos.AssignDriverResponseDto;

public interface BookingService {
    CreateBookingResponseDto createBooking(CreateBookingRequestDto createBookingRequestDto);

    AssignDriverResponseDto updateBookingStatusAndDriver(AssignDriverRequestDto updateBookingRequestDto, Long bookingId);
}
