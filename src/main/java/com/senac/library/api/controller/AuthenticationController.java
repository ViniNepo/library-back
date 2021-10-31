package com.senac.library.api.controller;

import com.senac.library.api.model.dto.LoginDto;
import com.senac.library.api.model.dto.TokenDto;
import com.senac.library.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Profile(value = {"prod", "test"})
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody LoginDto loginForm) {
        UsernamePasswordAuthenticationToken loginData = loginForm.convert();

        try {
            Authentication authenticate = authManager.authenticate(loginData);
            String token = tokenService.generateToken(authenticate);
            return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
