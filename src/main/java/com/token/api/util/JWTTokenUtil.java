package com.token.api.util;

import com.token.api.dto.user.UserDetailsDTO;
import com.token.api.dto.token.ValidateTokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTTokenUtil {

    private static final long serialVersionUID = -3650185175121007566L;

    @Value("${jwt.secret}")
    private String jwtSecret; // seconds

    @Value("${jwt.tokenlife}")
    private Long jwtTokenLife;

    /**
     * Generate Token for User
     * */
    public String generateToken(UserDetailsDTO userDetailsDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetailsDTO.getRequestedSystem());
        return doGenerateToken(claims, userDetailsDTO.getSystemUsername());
    }

    /**
     * Validate Token
     * */
    public Boolean validateToken(ValidateTokenDTO validateToken) {
        return (getUsernameFromToken(validateToken.getToken()).equals(validateToken.getSystemUsername()) && !isTokenExpired(validateToken.getToken()));
    }

    /**
     * Get Username from JWT
     * */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Get Expire Date from JWT
     * */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Get All Info from JWT
     * */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    /**
     * Check Token is expired
     * */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Create Token
     *
     * */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenLife * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

}
