package org.shakti.uberauthservice.Services;

import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;

public interface AuthService {
    public PassengerResponseDto signUp(PassengerSignupRequestDto passengerSignupRequestDto);
}
