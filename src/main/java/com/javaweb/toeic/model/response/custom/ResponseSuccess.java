//package com.javaweb.toeic.model.response.custom;
//
//import lombok.Getter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//
//public class ResponseSuccess extends ResponseEntity<ResponseSuccess.Payload> {
//    // Put, Patch, delete, tra ve HttpStatus.OK o day giai quyet van de 204 cua delete
//    public ResponseSuccess(HttpStatusCode status, String message) {
//        super(new Payload(status.value(), message), HttpStatus.OK);
//    }
//
//    // get, post
//    public ResponseSuccess(HttpStatusCode status, String message, Object data) {
//        super(new Payload(status.value(), message, data), HttpStatus.OK);
//    }
//
//    @Getter
//    public static class Payload{
//        private final int status;
//        private final String message;
//        private Object data;
//
//        public Payload(int status, String message, Object data) {
//            this.status = status;
//            this.message = message;
//            this.data = data;
//        }
//
//        public Payload(int status, String message) {
//            this.status = status;
//            this.message = message;
//        }
//    }
//}
