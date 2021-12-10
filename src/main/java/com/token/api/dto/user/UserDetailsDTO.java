package com.token.api.dto.user;
import javax.validation.constraints.NotNull;

public class UserDetailsDTO {

    @NotNull(message="System Username can not be null")
    private String systemUsername;

    @NotNull(message="System Password can not be null")
    private String systemPassword;

    @NotNull(message="Requested System can not be null")
    private String requestedSystem;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(String systemUsername, String systemPassword, String requestedSystem) {
        this.systemUsername = systemUsername;
        this.systemPassword = systemPassword;
        this.requestedSystem = requestedSystem;
    }

    public String getSystemUsername() {
        return systemUsername;
    }

    public void setSystemUsername(String systemUsername) {
        this.systemUsername = systemUsername;
    }

    public String getSystemPassword() {
        return systemPassword;
    }

    public void setSystemPassword(String systemPassword) {
        this.systemPassword = systemPassword;
    }

    public String getRequestedSystem() {
        return requestedSystem;
    }

    public void setRequestedSystem(String requestedSystem) {
        this.requestedSystem = requestedSystem;
    }

}
