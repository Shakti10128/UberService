package org.shakti.uberauthservice.Controllers;

import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;
import org.shakti.uberauthservice.Services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerResponseDto> signUp(@RequestBody PassengerSignupRequestDto request){
       PassengerResponseDto response = authService.signUp(request);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<Map<String,Object>> signIn(@RequestBody Map<String,String> request){
        String email = request.get("email");
        String password = request.get("password");
        PassengerResponseDto response = authService.signIn(email,password);
        Map<String,Object> responseMap = Map.of(
                "success","true",
                "JWT","abc",
                "data",response
        );
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
