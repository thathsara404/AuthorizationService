package com.token.api.controller;

import com.token.api.constant.ErrorConstant;
import com.token.api.constant.SuccessMessage;
import com.token.api.data.error.ErrorDetailsDTO;
import com.token.api.dto.common.CommonResponseDTO;
import com.token.api.dto.user.UserDetailsDTO;
import com.token.api.dto.token.ValidateTokenDTO;
import com.token.api.exception.UserException;
import com.token.api.service.ISystemTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping("/token-api")
public class SystemController {

    @Autowired
    private ISystemTokenService systemTokenService;

    @PostMapping(path = "/getToken", consumes = "application/json", produces = "application/json")
    public ResponseEntity getSystemToken(@NotNull @RequestHeader Map<String, String> headers) {
        try {
            String systemUsername = headers.containsKey("systemusername") && !StringUtils.isEmpty(headers.get("systemusername")) ? headers.get("systemusername") : null;
            String systemPassword = headers.containsKey("systempassword") && !StringUtils.isEmpty(headers.get("systempassword")) ? headers.get("systempassword") : null;
            String requestedSystem = headers.containsKey("requestedsystem") && !StringUtils.isEmpty(headers.get("requestedsystem")) ? headers.get("requestedsystem") : null;
            if (systemUsername != null && systemPassword != null && requestedSystem != null) {
                UserDetailsDTO userDetailsDTO = new UserDetailsDTO(systemUsername, systemPassword, requestedSystem);
                return ResponseEntity.status(HttpStatus.OK).body(systemTokenService.createToken(userDetailsDTO));
            } else {
                return ResponseEntity.badRequest()
                        .body(new ErrorDetailsDTO(ErrorConstant.BAD_REQUEST));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ErrorConstant.USER_NOT_FOUND);
        }
    }

    @PostMapping(path = "/validateToken")
    public ResponseEntity validateSystemToken(@Valid @RequestBody ValidateTokenDTO validateToken) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(systemTokenService.validateToken(validateToken));
        } catch (UserException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDetailsDTO(ErrorConstant.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping(path = "/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity createUser(@Valid @RequestBody UserDetailsDTO userDetailsDTO) {
        UserDetailsDTO savedUserDetailsDTO = null;
        try {
            savedUserDetailsDTO = systemTokenService.createUser(userDetailsDTO);
        } catch (UserException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDetailsDTO(exception.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseDTO(SuccessMessage.USER_ADDED_SUCCESSFULLY));
    }

}
