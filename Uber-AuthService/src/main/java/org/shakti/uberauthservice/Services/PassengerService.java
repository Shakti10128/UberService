package org.shakti.uberauthservice.Services;

import org.shakti.uberauthservice.Dtos.PassengerResponseDto;
import org.shakti.uberauthservice.Dtos.PassengerSignupRequestDto;

public interface PassengerService {
    PassengerResponseDto signUp(PassengerSignupRequestDto passengerSignupRequestDto);
}
