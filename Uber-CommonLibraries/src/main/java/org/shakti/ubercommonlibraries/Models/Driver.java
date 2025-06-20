package org.shakti.ubercommonlibraries.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.shakti.ubercommonlibraries.Enums.DriverApprovalStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseModel{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    private String adharCardNumber;

    @Enumerated(value = EnumType.STRING)
    private DriverApprovalStatus approvalStatus;

    @OneToOne
    private ExactLocation lastKnownLocation;

    private String activeCity;

    @OneToOne
    private ExactLocation home;

    @DecimalMin(value="0.01", message = "Rating must be greater than or equal to 0.00")
    @DecimalMax(value = "5.00", message = "Rating must be less than or equal to 5.00")
    private double rating;

    // 1 Driver can have many bookings
    @OneToMany(mappedBy = "driver",fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT) // To solve the N+1 problem
    private List<Booking> bookings = new ArrayList<>();
}
