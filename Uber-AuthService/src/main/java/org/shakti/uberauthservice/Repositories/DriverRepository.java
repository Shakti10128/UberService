package org.shakti.uberauthservice.Repositories;

import org.shakti.ubercommonlibraries.Models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    boolean existsByEmail(String email);
    Driver findByEmail(String email);
}
