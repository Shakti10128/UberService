package org.shakti.uberbookingservice.Dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DriverLocationDto {
    private String driverId;
    private double latitude;
    private double longitude;
}
