package com.token.api.dto.token;

import javax.validation.constraints.NotNull;

public class ValidateTokenDTO {

    @NotNull(message = "Token can not be null")
    private String token;

    @NotNull(message = "System username can not be null")
    private String systemUsername;

    public ValidateTokenDTO(@NotNull(message = "Token can not be null") String token, @NotNull(message = "System username can not be null") String systemUsername) {
        this.token = token;
        this.systemUsername = systemUsername;
    }

    public String getToken() {
        return token;
    }

    public String getSystemUsername() {
        return systemUsername;
    }

}
