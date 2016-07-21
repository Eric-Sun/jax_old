package com.j13.bar.server.exceptions;

public class PasswordErrorException extends Exception {
    public PasswordErrorException(String msg) {
        super(msg);
    }

    public PasswordErrorException(String msg, Throwable t) {
        super(msg, t);
    }
}
