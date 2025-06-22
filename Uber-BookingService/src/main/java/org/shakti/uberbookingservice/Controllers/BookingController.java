package org.shakti.uberbookingservice.Controllers;


import org.shakti.uberbookingservice.Dtos.CreateBookingRequestDto;
import org.shakti.uberbookingservice.Dtos.CreateBookingResponseDto;
import org.shakti.uberbookingservice.Services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/new-booking")
    public ResponseEntity<CreateBookingResponseDto> createBookingHandler(@RequestBody CreateBookingRequestDto requestDto) {
        CreateBookingResponseDto response = bookingService.createBooking(requestDto);
        return ResponseEntity.ok().body(response);
    }
}
