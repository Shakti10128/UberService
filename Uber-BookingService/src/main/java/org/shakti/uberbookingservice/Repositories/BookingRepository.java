package org.shakti.uberbookingservice.Repositories;

import org.shakti.ubercommonlibraries.Models.Booking;
import org.shakti.ubercommonlibraries.Models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findBookingById(Long id);

    boolean existsBookingById(Long id);

    @Query("UPDATE Booking b SET b.bookingStatus =:bookingStatus, b.driver =:driver where b.id=:id")
    Booking updateBookingStatusAndDriverById(@Param("id") Long id, @Param("bookingStatus") String bookingStatus, @Param("driver")Driver driver);
}
