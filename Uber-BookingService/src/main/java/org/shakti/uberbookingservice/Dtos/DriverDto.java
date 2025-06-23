package org.shakti.uberbookingservice.Dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DriverDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String licenseNumber;
}
