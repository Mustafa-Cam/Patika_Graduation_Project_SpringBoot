package com.patika.adservice.exception;


import com.patika.adservice.dto.response.ExceptionResponse;
import com.patika.adservice.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(PatikaException.class)
    public GenericResponse<ExceptionResponse> handleException(PatikaException exception) {
        log.error(exception.getLocalizedMessage());
        return GenericResponse.failed(exception.getMessage());
    }

}
