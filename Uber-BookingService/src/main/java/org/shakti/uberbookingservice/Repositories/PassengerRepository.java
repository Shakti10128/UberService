package org.shakti.uberbookingservice.Repositories;

import org.shakti.ubercommonlibraries.Models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Boolean existsById(long id);
    Optional<Passenger> findById(long id);
}
