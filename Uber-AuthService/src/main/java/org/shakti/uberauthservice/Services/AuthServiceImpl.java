package org.shakti.uberauthservice.Services;

import jakarta.transaction.Transactional;
import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;
import org.shakti.uberauthservice.Models.Passenger;
import org.shakti.uberauthservice.Repositories.PassengerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(PassengerRepository passengerRepository, PasswordEncoder passwordEncoder) {
        this.passengerRepository = passengerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public PassengerResponseDto signUp(PassengerSignupRequestDto passengerSignupRequestDto) {
        try{
            // first check either the email exist or not
            if(passengerRepository.existsByEmail(passengerSignupRequestDto.getEmail())){
                throw new RuntimeException("Email already register with our service");
            }
            passengerSignupRequestDto.setPassword(passwordEncoder.encode(passengerSignupRequestDto.getPassword()));
            Passenger passenger = PassengerSignupRequestDto.toPassenger(passengerSignupRequestDto);
            passengerRepository.save(passenger);
            return PassengerResponseDto.toPassengerResponseDto(passenger);
        }
        catch (Exception e){
            throw (RuntimeException) e;
        }
    }
}
