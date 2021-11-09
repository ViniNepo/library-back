package com.senac.library.api.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class DashboardException extends RuntimeException {

    public DashboardException(String message) {
        super(message);
    }

    public static RuntimeException dashboardException(String message) {
        throw new RuntimeException(message);
    }

}
