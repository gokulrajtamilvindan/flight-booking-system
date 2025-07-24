package com.fbs.central_api.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthUtility {
    @Value("${jwt.expiration.time}")
    long expirationTime;

    @Value("${jwt.secret.password}")
    String secretPassword;

    // JWT Token -> Header, Payload, Signature(Combination of Secure Password + Algorithm)
    // JWT Token -> We are Encrypting the data with the help of Algorithm + secretPassword

    public String generateToken(String email,
                                String password,
                                String role) {
        String payload = email + ":" + password + ":" + role;
        String jwtToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretPassword)
                .setSubject(payload)
                .compact();
        return jwtToken;
    }

    /**
     * This method is responsible for decrypting the token and taking out the encrypted payload
     * @param token
     * @return
     */

    public String decryptJwtToken(String token) {
        String payload = Jwts.parser().setSigningKey(secretPassword)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return payload;
    }
}
