package org.shakti.uberbookingservice.Apis;

import org.shakti.uberbookingservice.Dtos.DriverLocationDto;
import org.shakti.uberbookingservice.Dtos.NearbyDriversRequestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationServiceApi {
    @POST("/api/v1/location/drivers/nearby")
    Call<DriverLocationDto[]> nearbyDrivers(@Body NearbyDriversRequestDto nearbyDriversRequestDto);
}
