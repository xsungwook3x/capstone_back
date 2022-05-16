package com.develop.demo.dto;
import lombok.*;
import org.springframework.stereotype.Service;

import java.security.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendSmsResponseDTO {
    private String statusCode;
    private String statusName;
    private String requestId;
    private Timestamp requestTime;

}
