package org.shakti.uberauthservice.Dtos;

import lombok.*;
import org.shakti.ubercommonlibraries.Models.Passenger;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PassengerResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private double rating;

    public static PassengerResponseDto toPassengerResponseDto(Passenger passenger) {
        return PassengerResponseDto.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .email(passenger.getEmail())
                .phoneNumber(passenger.getPhoneNumber())
                .rating(passenger.getRating())
                .build();
    }
}
