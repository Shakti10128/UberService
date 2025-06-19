package org.shakti.uberauthservice.Services;

import org.shakti.uberauthservice.Exceptions.CustomError;
import org.shakti.uberauthservice.Repositories.DriverRepository;
import org.shakti.uberauthservice.Repositories.PassengerRepository;
import org.shakti.uberauthservice.helpers.AuthDriverDetails;
import org.shakti.uberauthservice.helpers.AuthPassengerDetails;
import org.shakti.ubercommonlibraries.Models.Driver;
import org.shakti.ubercommonlibraries.Models.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;

    public UserDetailsServiceImpl(PassengerRepository passengerRepository, DriverRepository driverRepository) {
        this.passengerRepository = passengerRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check for Passenger
        if (passengerRepository.existsByEmail(username)) {
            Passenger passenger = passengerRepository.findByEmail(username);
            return new AuthPassengerDetails(passenger);
        }

        // Check for Driver
        if (driverRepository.existsByEmail(username)) {
            Driver driver = driverRepository.findByEmail(username);
            return new AuthDriverDetails(driver);
        }

        // If not found in either
        throw new CustomError("User not found with email: " + username, HttpStatus.BAD_REQUEST);
    }
}
