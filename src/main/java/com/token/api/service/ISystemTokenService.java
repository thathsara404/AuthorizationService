package com.token.api.service;

import com.token.api.dto.jwt.JwtTokenResponseDTO;
import com.token.api.dto.user.UserDetailsDTO;
import com.token.api.dto.token.ValidTokenResponseDTO;
import com.token.api.dto.token.ValidateTokenDTO;
import com.token.api.exception.UserException;

public interface ISystemTokenService {

    public UserDetailsDTO createUser(UserDetailsDTO userDetailsDTO) throws UserException;

    public JwtTokenResponseDTO createToken(UserDetailsDTO userDetailsDTO) throws UserException;

    public ValidTokenResponseDTO validateToken(ValidateTokenDTO validateToken) throws UserException;

}
