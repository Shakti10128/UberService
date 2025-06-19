package org.shakti.ubercommonlibraries.Models;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Random;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OTP extends BaseModel{
    private String otp;

    private String sentToNumber;

    public static OTP makeOTP(String sentToNumber) {
        Random random = new Random();
        Integer randomInt = random.nextInt(9999) + 1000;
        return OTP.builder()
                .otp(randomInt.toString())
                .sentToNumber(sentToNumber)
                .build();
    }

}
