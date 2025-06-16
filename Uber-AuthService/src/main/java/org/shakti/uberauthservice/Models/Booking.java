package org.shakti.uberauthservice.Models;

import jakarta.persistence.*;
import lombok.*;
import org.shakti.uberauthservice.Enums.BookingStatus;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel{
    @OneToOne
    @JoinColumn(name = "review_id")  // foreign key in booking table
    private Review review; // defines 1:1 relation between Booking & Review

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;

    @ManyToOne
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;
}
