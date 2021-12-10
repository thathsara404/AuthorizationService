package com.token.api.dto.jwt;

import javax.validation.constraints.NotNull;

public class JwtTokenResponseDTO {

    private String token;

    public JwtTokenResponseDTO() {
    }

    public JwtTokenResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtToken{" +
                "token='" + token + '\'' +
                '}';
    }

}
