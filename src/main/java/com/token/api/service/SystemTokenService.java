package com.token.api.service;

import com.token.api.constant.ErrorConstant;
import com.token.api.data.contract.IUserDataContract;
import com.token.api.data.model.UserModel;
import com.token.api.dto.jwt.JwtTokenResponseDTO;
import com.token.api.dto.token.ValidTokenResponseDTO;
import com.token.api.dto.user.UserDetailsDTO;
import com.token.api.dto.token.ValidateTokenDTO;
import com.token.api.exception.UserException;
import com.token.api.util.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;


@Service
public class SystemTokenService implements  ISystemTokenService{

    private static final Logger LOGGER = Logger.getLogger(SystemTokenService.class.getName());

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private IUserDataContract userDataContract;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsDTO createUser(UserDetailsDTO userDetailsDTO) throws UserException {
        try {
            if( userDataContract.findByUserName(userDetailsDTO.getSystemUsername()) == null ) {
                userDataContract.addUser(userDetailsDTO.getRequestedSystem(), passwordEncoder.encode(userDetailsDTO.getSystemPassword()), userDetailsDTO.getSystemUsername());
            } else {
                throw new UserException(ErrorConstant.USER_ALREADY_EXIST);
            }
        } catch (Exception exception) {
            throw new UserException(ErrorConstant.INTERNAL_SERVER_ERROR);
        }
        return userDetailsDTO;
    }

    @Override
    public JwtTokenResponseDTO createToken(UserDetailsDTO userDetailsDTO) throws UserException {
        try {
            UserModel userModel = userDataContract.findByUserName(userDetailsDTO.getSystemUsername());
            if( userModel != null && passwordEncoder.matches(userDetailsDTO.getSystemPassword(), userModel.getPassword())) {
                return new JwtTokenResponseDTO(jwtTokenUtil.generateToken(userDetailsDTO));
            } else {
                throw new UserException(ErrorConstant.USER_NOT_FOUND);
            }
        } catch (Exception exception) {
            throw new UserException(ErrorConstant.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ValidTokenResponseDTO validateToken(ValidateTokenDTO validateToken) throws UserException {
        try {
            if( jwtTokenUtil.validateToken(validateToken) ) {
                return new ValidTokenResponseDTO(true);
            }
        } catch (Exception exception) {
            LOGGER.error("Error occurred while validating token...");
            throw new UserException(exception.getMessage());
        }
        return new ValidTokenResponseDTO(false);
    }
}
