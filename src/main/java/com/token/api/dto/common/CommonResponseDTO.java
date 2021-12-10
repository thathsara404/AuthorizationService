package com.token.api.dto.common;

public class CommonResponseDTO {

    private String status;

    public CommonResponseDTO() {
    }

    public CommonResponseDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

