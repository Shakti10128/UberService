package org.shakti.uberwebsocketservice.Dtos;


import lombok.*;
import org.shakti.uberwebsocketservice.Models.ExactLocation;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideRequestDto {
    // passenger id, who is going to make a request for the ride
    private Long passengerId;
    // passenger start location
//    private ExactLocation startLocation;
//    private ExactLocation endLocation;
    // nearby driver ids
    List<Long> driverIds;
}
