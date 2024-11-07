package com.javaweb.toeic.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

// MethodArgumentNotValidException : controller vi phạm : @Valid, vd như dto đẩy lên sai
// MethodArgumentTypeMismatchException: controller vi phạm pathvariable như truyên lên string nhưng nhận long thì parse ko đc
// HandlerMethodValidationException: controller vi phạm pathvariable như @Min(1) mà đẩy lên < 1
// ConstraintViolationException: ràng buoc của entity bị vi phạm

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception e, WebRequest request) {
        System.out.println("handleValidationException");
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .path(request.getDescription(false).replace("uri=", ""))
                .error(e instanceof MethodArgumentNotValidException ? "Payload invalid" : "Parameter invalid")
                .message(e instanceof MethodArgumentNotValidException
                        ? e.getMessage().substring(e.getMessage().lastIndexOf('['), e.getMessage().lastIndexOf(']'))
                        : e instanceof ConstraintViolationException ?
                        e.getMessage().substring(e.getMessage().lastIndexOf(' ') + 1)
                        : e.getMessage()
                        )
                .build();
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HandlerMethodValidationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerErrorException(Exception e, WebRequest request) {
        System.out.println("MethodArgumentTypeMismatchException and HandlerMethodValidationException");
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date())
                .path(request.getDescription(false).replace("uri=", ""))
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("PathVariable false")
                .build();
    }
}
