package com.ibm.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message",ex.getReason());
        body.put("error", HttpStatus.resolve(ex.getStatusCode().value()).getReasonPhrase());
        body.put("status",ex.getStatusCode().value());

        return new ResponseEntity<>(body,ex.getStatusCode());
    }

}
