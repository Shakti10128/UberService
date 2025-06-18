package org.shakti.uberauthservice.Services;

import org.shakti.uberauthservice.Dtos.DriverSignupRequestDto;
import org.shakti.uberauthservice.Models.Driver;

public interface DriverService {
    Driver signUp(DriverSignupRequestDto requestDto);
}
