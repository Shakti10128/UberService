package org.shakti.ubercommonlibraries.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Color extends BaseModel{
    @Column(unique=true, nullable=false)
    private String color;
}
