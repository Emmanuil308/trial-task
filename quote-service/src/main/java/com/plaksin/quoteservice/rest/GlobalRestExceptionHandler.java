package com.plaksin.quoteservice.rest;

import com.plaksin.quoteservice.exception.EntityNotFoundException;
import com.plaksin.quoteservice.model.response.Response;
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
    @ExceptionHandler(EntityNotFoundException.class)
    public <T> Response<T> handleConflict(EntityNotFoundException ex) {
        return Response.error(460, ex.getMessage());
    }
}

