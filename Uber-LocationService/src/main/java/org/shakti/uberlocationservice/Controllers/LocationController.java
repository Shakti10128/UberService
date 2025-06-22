package org.shakti.uberlocationservice.Controllers;

import org.shakti.uberlocationservice.Dtos.DriverLocationDto;
import org.shakti.uberlocationservice.Dtos.NearbyDriversRequestDto;
import org.shakti.uberlocationservice.Dtos.SaveDriverLocationRequestDto;
import org.shakti.uberlocationservice.Services.LocationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    private final LocationServices locationServices;

    public LocationController(LocationServices locationServices) {
        this.locationServices = locationServices;
    }

    @PostMapping("/drivers")
    public ResponseEntity<Boolean> saveDriverLocationHandler(@RequestBody SaveDriverLocationRequestDto driverLocationRequestDto) {
        Boolean isLocationSaved = locationServices.saveDriverLocation(driverLocationRequestDto);
        return ResponseEntity.ok(isLocationSaved);
    }

    @PostMapping("/drivers/nearby")
    public ResponseEntity<List<DriverLocationDto>> getNearbyDriversHandler(@RequestBody NearbyDriversRequestDto nearbyDriversRequestDto) {
        List<DriverLocationDto> res = locationServices.getNearbyDrivers(nearbyDriversRequestDto);
        return ResponseEntity.ok(res);
    }
}
