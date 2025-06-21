package org.shakti.uberlocationservice.Services;

import org.shakti.uberlocationservice.Dtos.DriverLocationDto;
import org.shakti.uberlocationservice.Dtos.NearbyDriversRequestDto;
import org.shakti.uberlocationservice.Dtos.SaveDriverLocationRequestDto;

import java.util.List;

public interface LocationServices {
    Boolean saveDriverLocation(SaveDriverLocationRequestDto driverLocationRequestDto);

    List<DriverLocationDto> getNearbyDrivers(NearbyDriversRequestDto nearbyDriversRequestDto);
}
