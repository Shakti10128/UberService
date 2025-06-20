package org.shakti.uberauthservice.Dtos;

import lombok.*;
import org.shakti.ubercommonlibraries.Models.Passenger;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private double rating = 0.01;

    public static Passenger toPassenger(PassengerSignupRequestDto passengerSignupRequestDto) {
        return Passenger.builder()
                .email(passengerSignupRequestDto.getEmail())
                .password(passengerSignupRequestDto.getPassword())
                .phoneNumber(passengerSignupRequestDto.phoneNumber)
                .name(passengerSignupRequestDto.getName())
                .rating(passengerSignupRequestDto.getRating())
                .build();
    }
}
