package org.shakti.uberauthservice.Services;

import org.shakti.uberauthservice.Dtos.DriverSignupRequestDto;
import org.shakti.uberauthservice.Exceptions.CustomError;
import org.shakti.uberauthservice.Repositories.DriverRepository;
import org.shakti.ubercommonlibraries.Models.Driver;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;

    public DriverServiceImpl(DriverRepository driverRepository, PasswordEncoder passwordEncoder) {
        this.driverRepository = driverRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Driver signUp(DriverSignupRequestDto requestDto) {
        try{
            // check all mandatory fields are provided or not
            if(requestDto.getName() == null || requestDto.getEmail() == null || requestDto.getPassword() == null
            || requestDto.getLicenseNumber() == null || requestDto.getPhoneNumber() == null
            || requestDto.getAddress() == null){
                throw new CustomError("All fields are required", HttpStatus.BAD_REQUEST);
            }

            // check Driver already registered or not
            if(driverRepository.existsByEmail(requestDto.getEmail())){
                throw new CustomError("Email already exists", HttpStatus.CONFLICT);
            }

            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            Driver driver = DriverSignupRequestDto.toDriver(requestDto);

            driverRepository.save(driver);
            return driver;
        }
        catch(Exception e){
            if(e instanceof CustomError) throw (CustomError)e;
            throw e;
        }
    }
}
