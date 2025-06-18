package org.shakti.uberauthservice.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.shakti.uberauthservice.Dtos.AuthRequestDto;
import org.shakti.uberauthservice.Dtos.DriverSignupRequestDto;
import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;
import org.shakti.uberauthservice.Models.Driver;
import org.shakti.uberauthservice.Services.AuthService;
import org.shakti.uberauthservice.Services.DriverService;
import org.shakti.uberauthservice.Services.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final PassengerService passengerService;
    private final DriverService driverService;

    public AuthController(AuthService authService, PassengerService passengerService, DriverService driverService) {
        this.authService = authService;
        this.passengerService = passengerService;
        this.driverService = driverService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerResponseDto> signUpPassengerHandler(@RequestBody PassengerSignupRequestDto request){
        PassengerResponseDto response = passengerService.signUp(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<Map<String,Object>> signInPassengerHandler(@RequestBody AuthRequestDto request,HttpServletResponse response){
        return new ResponseEntity<>(singInHelper(request,response), HttpStatus.OK);
    }

    @PostMapping("/signup/driver")
    public ResponseEntity<Driver> signUpDriverHandler(@RequestBody DriverSignupRequestDto driverSignupRequestDto){
        Driver driver = driverService.signUp(driverSignupRequestDto);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @PostMapping("/signin/driver")
    public ResponseEntity<Map<String,Object>> signInDriverHandler(@RequestBody AuthRequestDto request,HttpServletResponse response){
        return new ResponseEntity<>(singInHelper(request,response), HttpStatus.OK);
    }

    public Map<String, Object> singInHelper(AuthRequestDto request, HttpServletResponse response){
        String jwtToken = authService.signIn(request,response);
        return Map.of(
                "success","true",
                "message","Logged in successfully",
                "token",jwtToken
        );
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String,Object>> validate(){
        return new ResponseEntity<>(Map.of("success","true"), HttpStatus.OK);
    }
}
