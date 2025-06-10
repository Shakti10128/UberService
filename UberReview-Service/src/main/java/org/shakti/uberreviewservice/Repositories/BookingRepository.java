package org.shakti.uberreviewservice.Repositories;

import org.shakti.uberreviewservice.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
