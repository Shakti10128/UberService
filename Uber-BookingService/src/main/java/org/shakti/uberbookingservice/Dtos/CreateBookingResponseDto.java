package org.shakti.uberbookingservice.Dtos;


import lombok.*;
import org.shakti.ubercommonlibraries.Models.Driver;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingResponseDto {
    private Long bookingId;
    private String bookingStatus;
    private Optional<Driver> driver;
}
