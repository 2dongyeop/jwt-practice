package io.wisoft.leedongyeop.jwtpractice.controller.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String name;
    private String pwd;
}
