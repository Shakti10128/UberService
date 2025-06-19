package org.shakti.uberauthservice.Services;

import org.shakti.uberauthservice.Dtos.DriverSignupRequestDto;
import org.shakti.ubercommonlibraries.Models.Driver;

public interface DriverService {
    Driver signUp(DriverSignupRequestDto requestDto);
}
