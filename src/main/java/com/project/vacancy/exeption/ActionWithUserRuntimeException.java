package com.project.vacancy.exeption;

public class ActionWithUserRuntimeException extends RuntimeException {
    public ActionWithUserRuntimeException() {
    }

    public ActionWithUserRuntimeException(String message) {
        super(message);
    }

    public ActionWithUserRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
