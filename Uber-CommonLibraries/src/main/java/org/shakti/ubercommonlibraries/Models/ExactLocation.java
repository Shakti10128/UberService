package org.shakti.ubercommonlibraries.Models;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExactLocation extends BaseModel{
    private Double latitude;
    private Double longitude;
}
