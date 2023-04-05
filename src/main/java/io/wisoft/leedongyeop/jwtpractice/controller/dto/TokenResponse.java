package io.wisoft.leedongyeop.jwtpractice.controller.dto;

import lombok.Getter;

@Getter
public class TokenResponse {
    private String accessToken;
    private String tokenType;

    public TokenResponse(String token, String tokenType) {
        this.accessToken = token;
        this.tokenType = tokenType;
    }
}
