package org.shakti.ubercommonlibraries.Models;

import jakarta.persistence.*;
import lombok.*;
import org.shakti.ubercommonlibraries.Enums.CarType;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseModel{
    private String plateNumber;

    private String brand;

    private String model;

    @ManyToOne
    private Color color;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    @OneToOne
    private Driver driver;
}
