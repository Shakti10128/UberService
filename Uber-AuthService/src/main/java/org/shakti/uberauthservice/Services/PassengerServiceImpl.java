package org.shakti.uberauthservice.Services;

import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;
import org.shakti.uberauthservice.Exceptions.CustomError;
import org.shakti.uberauthservice.Repositories.PassengerRepository;
import org.shakti.ubercommonlibraries.Models.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;

    public PassengerServiceImpl(PassengerRepository passengerRepository,PasswordEncoder passwordEncoder) {
        this.passengerRepository = passengerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PassengerResponseDto signUp(PassengerSignupRequestDto passengerSignupRequestDto) {
        try{
            // first check either the email exist or not
            if(passengerRepository.existsByEmail(passengerSignupRequestDto.getEmail())){
                throw new CustomError("Email already register with our service", HttpStatus.CONFLICT);
            }
            passengerSignupRequestDto.setPassword(passwordEncoder.encode(passengerSignupRequestDto.getPassword()));
            Passenger passenger = PassengerSignupRequestDto.toPassenger(passengerSignupRequestDto);
            passengerRepository.save(passenger);
            return PassengerResponseDto.toPassengerResponseDto(passenger);
        }
        catch (Exception e){
            if(e instanceof CustomError) throw (CustomError)e;
            throw e;
        }
    }
}
