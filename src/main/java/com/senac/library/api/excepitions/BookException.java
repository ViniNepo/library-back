package com.senac.library.api.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class BookException extends RuntimeException {

    public BookException(String message) {
        super(message);
    }

    public static RuntimeException bookException(String message) {
        throw new RuntimeException(message);
    }

}
