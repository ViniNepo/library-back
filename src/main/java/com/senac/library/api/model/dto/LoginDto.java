package com.senac.library.api.model.dto;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Data
public class LoginDto {

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
