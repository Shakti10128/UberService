package org.shakti.uberbookingservice.Dtos;


import lombok.*;
import org.shakti.ubercommonlibraries.Enums.BookingStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AssignDriverRequestDto {
    private BookingStatus bookingStatus;
    private Long driverId;
}
