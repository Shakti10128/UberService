package org.shakti.uberbookingservice.Repositories;

import org.shakti.ubercommonlibraries.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
