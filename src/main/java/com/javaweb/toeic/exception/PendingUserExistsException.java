package com.javaweb.toeic.exception;

public class PendingUserExistsException extends RuntimeException{
    public PendingUserExistsException(String message){
        super(message);
    }
}
