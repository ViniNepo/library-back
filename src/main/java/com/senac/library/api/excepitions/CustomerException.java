package com.senac.library.api.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class CustomerException extends RuntimeException {

    public CustomerException(String message) {
        super(message);
    }

    public static RuntimeException customerException(String message) {
        throw new RuntimeException(message);
    }

}
