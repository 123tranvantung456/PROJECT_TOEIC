package com.javaweb.toeic.model.response.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class ResponseData<T>{
    @JsonInclude(JsonInclude.Include.NON_NULL) // neu data null thi ko hien thi
    private T data;
    private final int status;
    private final String message;

   // get, post
   public ResponseData(T data, int status, String message) {
       this.data = data;
       this.status = status;
       this.message = message;
   }
    // put, patch, delete
   public ResponseData(int status, String message) {
       this.status = status;
       this.message = message;
   }
}
