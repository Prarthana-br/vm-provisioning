package com.ripple.vmprovisioning.service.exceptions;

import com.ripple.vmprovisioning.exceptions.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class VMExceptionHandler {

    @ExceptionHandler(value = DuplicateUserException.class)
    public ResponseEntity<Map<String, String>> handle(RestException t){

        LinkedHashMap<String, String> errorMap = new LinkedHashMap<>();
        errorMap.put("HTTP_STATUS_CODE", String.valueOf(t.getHttpStatusCode()));
        errorMap.put("ERROR_MESSAGE", t.getMessage());

        return new ResponseEntity<>(errorMap, HttpStatus.valueOf(t.getHttpStatusCode()));
    }
}
