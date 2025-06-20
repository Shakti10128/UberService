package org.shakti.uberauthservice.Dtos;

import lombok.*;
import org.shakti.ubercommonlibraries.Models.Driver;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverSignupRequestDto {
    private String name;
    private String email;
    private String password;
    private String licenseNumber;
    private String phoneNumber;
    private String address;
    private double rating = 0.01;

    public static Driver toDriver(DriverSignupRequestDto driverAuthRequestDto) {
        return Driver.builder()
                .address(driverAuthRequestDto.getAddress())
                .phoneNumber(driverAuthRequestDto.getPhoneNumber())
                .licenseNumber(driverAuthRequestDto.getLicenseNumber())
                .password(driverAuthRequestDto.getPassword())
                .email(driverAuthRequestDto.getEmail())
                .name(driverAuthRequestDto.getName())
                .rating(driverAuthRequestDto.getRating())
                .build();
    }

}
