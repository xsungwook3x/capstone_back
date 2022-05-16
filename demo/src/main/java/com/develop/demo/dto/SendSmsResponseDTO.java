package com.develop.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendSmsResponseDTO {
    private String statusCode;
    private String statusName;
    private String requestId;
    private Timestamp requestTime;

}
