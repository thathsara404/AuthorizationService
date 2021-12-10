package com.token.api.dto.token;

import javax.validation.constraints.NotNull;

public class ValidTokenResponseDTO {

    private Boolean isValidToken;

    public ValidTokenResponseDTO() {
    }

    public ValidTokenResponseDTO(Boolean isValidToken) {
        this.isValidToken = isValidToken;
    }

    public Boolean getValidToken() {
        return isValidToken;
    }

    public void setValidToken(Boolean validToken) {
        isValidToken = validToken;
    }

    @Override
    public String toString() {
        return "ValidTokenResponse{" +
                "isValidToken=" + isValidToken +
                '}';
    }

}
