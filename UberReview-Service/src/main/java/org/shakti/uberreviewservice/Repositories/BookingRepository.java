package org.shakti.uberreviewservice.Repositories;

import org.shakti.uberreviewservice.Models.Booking;
import org.shakti.uberreviewservice.Models.Driver;
import org.shakti.uberreviewservice.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findById(Long id);
}
