package com.plaksin.authservice.rest;

import com.plaksin.authservice.exception.ConstraintException;
import com.plaksin.authservice.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ConstraintException.class)
    public <T> Response<T> handleConflict(ConstraintException ex) {
        return Response.error(450, ex.getMessage());
    }
}

