package com.token.api.controller;

import com.token.api.constant.ErrorConstant;
import com.token.api.data.error.ErrorDetailsDTO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ResponseEntity error() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDetailsDTO(ErrorConstant.BAD_REQUEST));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
