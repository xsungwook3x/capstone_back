package com.develop.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String token;
    private String userId;
    private String username;
    private String password;
    private String email;
    private String role;
    private Long id;
}
