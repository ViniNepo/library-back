package com.senac.library.api.excepitions;

public class CustomerException extends RuntimeException {

    public static RuntimeException customerException(String message) {
        throw new RuntimeException(message);
    }

}
