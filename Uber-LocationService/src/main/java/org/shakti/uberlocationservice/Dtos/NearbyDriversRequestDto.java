package org.shakti.uberlocationservice.Dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NearbyDriversRequestDto {
    private double latitude;
    private double longitude;
    // user want to search in specific range of 2km or 5km
    private double searchRadius;
}
