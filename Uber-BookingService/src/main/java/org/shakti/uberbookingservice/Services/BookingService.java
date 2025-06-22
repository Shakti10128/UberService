package org.shakti.uberbookingservice.Services;

import org.shakti.uberbookingservice.Dtos.CreateBookingRequestDto;
import org.shakti.uberbookingservice.Dtos.CreateBookingResponseDto;

public interface BookingService {
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto createBookingRequestDto);
}
