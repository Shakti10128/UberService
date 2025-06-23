package org.shakti.uberwebsocketservice.Controllers;

import org.shakti.uberwebsocketservice.Dtos.RideRequestDto;
import org.shakti.uberwebsocketservice.Dtos.RideResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/socket")
public class DriverRequestController {

    private SimpMessagingTemplate template;

    public DriverRequestController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @PostMapping("/new-ride")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDto rideRequestDto) {
        sendDriversNewRideRequest(rideRequestDto);
        return ResponseEntity.ok(true);
    }

    public void sendDriversNewRideRequest(RideRequestDto rideRequestDto) {
        // ideally the request should go to the nearby drivers, but we don't have frontend
        // so sending to all driver
        template.convertAndSend("/topic/rideRequest", rideRequestDto);
    }

    @MessageMapping("/rideResponse")
    public void rideResponseHandler(RideResponseDto rideResponseDto) {
        // if the request accepted by any driver send the response back to booking service
        System.out.println("is any driver accepted the ride request: " + rideResponseDto.isResponse());
    }
}
