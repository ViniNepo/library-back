package com.senac.library.api.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class SaleException extends RuntimeException {

    public SaleException(String message) {
        super(message);
    }

    public static RuntimeException saleException(String message) {
        throw new RuntimeException(message);
    }

}
