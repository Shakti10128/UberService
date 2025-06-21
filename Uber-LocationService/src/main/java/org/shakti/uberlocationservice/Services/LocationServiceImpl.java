package org.shakti.uberlocationservice.Services;

import org.shakti.uberlocationservice.Dtos.DriverLocationDto;
import org.shakti.uberlocationservice.Dtos.NearbyDriversRequestDto;
import org.shakti.uberlocationservice.Dtos.SaveDriverLocationRequestDto;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class LocationServiceImpl implements LocationServices{
    private final StringRedisTemplate stringRedisTemplate;
    private static final String DRIVER_GEO_OPS_KEY = "drivers";

    public LocationServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public Boolean saveDriverLocation(SaveDriverLocationRequestDto driverLocationRequestDto) {
        try{
            GeoOperations<String,String> geoOps = stringRedisTemplate.opsForGeo(); // operation for geoOps
            geoOps.add(DRIVER_GEO_OPS_KEY, new RedisGeoCommands.GeoLocation<>
                    (driverLocationRequestDto.getDriverId(),
                            new Point(
                                    driverLocationRequestDto.getLatitude(),
                                    driverLocationRequestDto.getLongitude())));
            return true;
        }
        catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<DriverLocationDto> getNearbyDrivers(NearbyDriversRequestDto nearbyDriversRequestDto) {
        try{
            GeoOperations<String,String> geoOps = stringRedisTemplate.opsForGeo();
            // we are searching in the 5km radius
            Distance radius = new Distance(nearbyDriversRequestDto.getSearchRadius(), Metrics.KILOMETERS);
            // but search in circular fashion
            // latitude & longitude for the exact location and radius for finding the center of the circle
            // why passed radius object: coz it has radius + unit of the radius
            Circle within = new Circle(new Point(nearbyDriversRequestDto.getLatitude(), nearbyDriversRequestDto.getLongitude()), radius);

            // search the results
            GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOps.radius(DRIVER_GEO_OPS_KEY, within);

            List<DriverLocationDto> drivers = new ArrayList<>();
            for(GeoResult<RedisGeoCommands.GeoLocation<String>> result: results) {
                String driverId = result.getContent().getName();

                // Fetch the actual coordinates (point) for this driver
                List<Point> positions = geoOps.position(DRIVER_GEO_OPS_KEY, driverId);

                if (positions == null || positions.isEmpty() || positions.get(0) == null) {
                    System.out.println("No geo-position found for driverId: {} " + driverId);
                    continue;
                }

                Point point = positions.get(0);

                DriverLocationDto driverLocationDto = DriverLocationDto.builder()
                        .driverId(driverId)
                        .latitude(point.getY())       // Latitude
                        .longitude(point.getX())      // Longitude
                        .build();

                drivers.add(driverLocationDto);
            }
            return drivers;
        } catch (Exception e) {
            throw e;
        }
    }
}
