package com.senac.library.api.excepitions;

public class BookException extends RuntimeException {

    public static RuntimeException bookException(String message) {
        throw new RuntimeException(message);
    }

}
