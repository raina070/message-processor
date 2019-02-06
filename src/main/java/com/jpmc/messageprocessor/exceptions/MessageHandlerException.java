package com.jpmc.messageprocessor.exceptions;

public class MessageHandlerException extends Exception {
    public MessageHandlerException(String message) {
        super(message);
    }

    public MessageHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
