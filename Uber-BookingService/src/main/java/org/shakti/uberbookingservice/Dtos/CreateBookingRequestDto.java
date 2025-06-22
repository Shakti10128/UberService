package org.shakti.uberbookingservice.Dtos;

import lombok.*;
import org.shakti.ubercommonlibraries.Enums.BookingStatus;
import org.shakti.ubercommonlibraries.Models.Booking;
import org.shakti.ubercommonlibraries.Models.ExactLocation;
import org.shakti.ubercommonlibraries.Models.Passenger;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingRequestDto {
    private Long passengerId;

    private ExactLocation startLocation;
    private ExactLocation endLocation;
}
