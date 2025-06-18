package org.shakti.uberauthservice.Services;

import jakarta.servlet.http.HttpServletResponse;
import org.shakti.uberauthservice.Dtos.AuthRequestDto;
import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;

import java.util.Map;


public interface AuthService {
    String signIn(AuthRequestDto authRequestDto, HttpServletResponse httpServletResponse);
}
