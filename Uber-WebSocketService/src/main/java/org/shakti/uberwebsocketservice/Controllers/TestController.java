package org.shakti.uberwebsocketservice.Controllers;
import org.shakti.uberwebsocketservice.Dtos.TestRequestDto;
import org.shakti.uberwebsocketservice.Dtos.TestResponseDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    private final SimpMessagingTemplate messagingTemplate;

    public TestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponseDto pingTestHandler(TestRequestDto requestDto) {
        System.out.println("Message received from the client is: " + requestDto.getData());
        return new TestResponseDto("Ping request received");
    }


    @Scheduled(fixedDelay = 2000)
    public void sendMessagePeriodically(){
        messagingTemplate.convertAndSend("/topic/scheduled", "Periodic message from the server: " + System.currentTimeMillis());
    }
}
