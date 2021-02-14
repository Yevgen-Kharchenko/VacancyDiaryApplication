package com.project.vacancy.exeption;

public class EntityNotExistRuntimeException extends RuntimeException {
    public EntityNotExistRuntimeException() {

    }

    public EntityNotExistRuntimeException(String message) {
        super(message);
    }

    public EntityNotExistRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
