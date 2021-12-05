package com.senac.library.api.service;

import com.senac.library.api.model.entities.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${library.jwt.expiration}")
    private String expiration;

    @Value("${library.jwt.secret}")
    private String secret;


    public String generateToken(Authentication authenticate) {
        Date today = new Date();
        Date expirationDate = new Date(today.getTime()+ Long.parseLong(expiration));

        Customer authenticatePrincipal = (Customer) authenticate.getPrincipal();

        return Jwts
                .builder()
                .setIssuer("Library API")
                .setSubject(authenticatePrincipal.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith((SignatureAlgorithm.HS256), secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {

            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdCustomer(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}
