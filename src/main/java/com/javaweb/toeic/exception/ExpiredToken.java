package com.javaweb.toeic.exception;

public class ExpiredToken extends Exception{
    public ExpiredToken(String msg) {
        super(msg);
    }
}
