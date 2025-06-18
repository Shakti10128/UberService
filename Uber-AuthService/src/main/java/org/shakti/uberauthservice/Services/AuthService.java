package org.shakti.uberauthservice.Services;

import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;

public interface AuthService {
    PassengerResponseDto signUp(PassengerSignupRequestDto passengerSignupRequestDto);

    PassengerResponseDto signIn(String email, String password);
}
