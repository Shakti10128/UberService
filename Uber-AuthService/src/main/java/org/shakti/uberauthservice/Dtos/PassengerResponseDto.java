package org.shakti.uberauthservice.Dtos;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponseDto {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
}
