package com.token.api.data.error;

import javax.validation.constraints.NotNull;

public class ErrorDetailsDTO {

    @NotNull
    private String error;

    public ErrorDetailsDTO() {
    }

    public ErrorDetailsDTO(@NotNull String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
