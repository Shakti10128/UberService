package org.shakti.uberreviewservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {
    private String content;
    private Double rating;
    private Long bookingId;
}
