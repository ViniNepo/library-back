package com.senac.library.api.mother;

import com.senac.library.api.model.dto.LoginDto;

public class LoginDtoMother {

    public static LoginDto createLoginDto() {
        LoginDto dto = new LoginDto();

        dto.setEmail("email@email.com");
        dto.setPassword("123");

        return dto;
    }
}
