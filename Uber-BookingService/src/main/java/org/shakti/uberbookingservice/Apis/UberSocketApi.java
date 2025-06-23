package org.shakti.uberbookingservice.Apis;
import org.shakti.uberbookingservice.Dtos.RideRequestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UberSocketApi {

    @POST("/api/v1/socket/new-ride")
    Call<Boolean> newRide(@Body RideRequestDto rideRequestDto);
}
