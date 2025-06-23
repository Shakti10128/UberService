package org.shakti.uberwebsocketservice.Models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExactLocation {
    private Double latitude;
    private Double longitude;
}
