package org.shakti.uberreviewservice.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookingreview")
public class Review extends BaseModel{
    @Column(nullable = false)
    private String content;

    private Double rating;
}
