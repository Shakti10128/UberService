package org.shakti.uberauthservice.Services;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.shakti.uberauthservice.Dtos.AuthRequestDto;
import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Value("${cookie.expire}")
    private int COOKIE_EXPIRE;

    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager
    , JwtServiceImpl jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public String signIn(AuthRequestDto authRequestDto, HttpServletResponse response) {
        String email = authRequestDto.getEmail();
        String password = authRequestDto.getPassword();
        try{
            // it is going to check itself either the user authenticated or not
            // if not it's throws exception
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            String jwtToken = jwtService.createToken(email);

            // creating the cookie for the token
            ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                    .httpOnly(true) // prevent JS access → secure
                    .path("/")      // makes cookie accessible to all paths on the domain
                    .maxAge(COOKIE_EXPIRE) // or use COOKIE_EXPIRE if defined as Duration
                    .secure(true)   // optional → recommended if using HTTPS
                    .build();


            // setting the cookie via header in the response
            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return jwtToken;
        }
        catch (Exception e){
            throw e;
        }
    }
}
