package org.shakti.uberbookingservice.Controllers;


import org.shakti.uberbookingservice.Dtos.CreateBookingRequestDto;
import org.shakti.uberbookingservice.Dtos.CreateBookingResponseDto;
import org.shakti.uberbookingservice.Dtos.AssignDriverRequestDto;
import org.shakti.uberbookingservice.Dtos.AssignDriverResponseDto;
import org.shakti.uberbookingservice.Services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/new-booking")
    public ResponseEntity<CreateBookingResponseDto> createBookingHandler(@RequestBody CreateBookingRequestDto requestDto) {
        return ResponseEntity.ok().body(bookingService.createBooking(requestDto));
    }

    @PatchMapping("/update-booking/{bookingId}")
    public ResponseEntity<AssignDriverResponseDto> updateBookingStatusAndDriverHandler(@RequestBody AssignDriverRequestDto requestDto, @PathVariable Long bookingId) {
        return ResponseEntity.ok().body(bookingService.updateBookingStatusAndDriver(requestDto, bookingId));
    }
}
