package com.kokobato.huynhduc.kokobatodemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRespone {
    private String message;
    private String token;
    private String refreshToken;
    private String username;
    private List<String> roles;
    private Integer userId;


}
