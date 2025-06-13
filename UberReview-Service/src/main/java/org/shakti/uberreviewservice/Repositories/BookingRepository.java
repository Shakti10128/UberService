package org.shakti.uberreviewservice.Repositories;

import org.shakti.uberreviewservice.Models.Booking;
import org.shakti.uberreviewservice.Models.Driver;
import org.shakti.uberreviewservice.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
