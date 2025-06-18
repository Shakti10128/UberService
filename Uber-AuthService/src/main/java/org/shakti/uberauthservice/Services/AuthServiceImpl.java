package org.shakti.uberauthservice.Services;

import jakarta.transaction.Transactional;
import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;
import org.shakti.uberauthservice.Exceptions.CustomError;
import org.shakti.uberauthservice.Models.Passenger;
import org.shakti.uberauthservice.Repositories.PassengerRepository;
import org.springframework.http.HttpStatus;
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

    @Override
    public PassengerResponseDto signIn(String email, String password) {
        try{
            // check email and password exist or not
            if(email == null || password == null){
                throw new CustomError("All fields are required", HttpStatus.BAD_REQUEST);
            }
            // check either the email is registered or not with our service\
            if(!passengerRepository.existsByEmail(email)){
                throw new CustomError("Email does not exist", HttpStatus.CONFLICT);
            }

            // get the passenger via email from DB
            Passenger passenger = passengerRepository.findByEmail(email);
            // check the passenger provided password and the DB hashPassword are matching or not
            if(!passwordEncoder.matches(password, passenger.getPassword())){
                throw new CustomError("Wrong password", HttpStatus.CONFLICT);
            }
            return PassengerResponseDto.toPassengerResponseDto(passenger);
        }
        catch (Exception e){
            if(e instanceof CustomError) throw (CustomError)e;
            throw e;
        }
    }
}
