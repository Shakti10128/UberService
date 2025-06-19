package org.shakti.uberauthservice.Repositories;

import org.shakti.ubercommonlibraries.Models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    boolean existsByEmail(String email);

    Passenger findByEmail(String email);
}
