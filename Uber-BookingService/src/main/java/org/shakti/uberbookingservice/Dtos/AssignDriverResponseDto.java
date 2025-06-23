package org.shakti.uberbookingservice.Dtos;

import lombok.*;
import org.shakti.ubercommonlibraries.Enums.BookingStatus;

import java.sql.Driver;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AssignDriverResponseDto {
    private Long bookingId;
    private BookingStatus bookingStatus;
    private DriverDto driver;
}
