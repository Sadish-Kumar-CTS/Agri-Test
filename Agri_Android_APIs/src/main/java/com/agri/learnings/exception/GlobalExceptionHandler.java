package com.agri.learnings.exception;

import com.agri.learnings.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomUserNotFoundException.class)
    public CommonResponse customUserNotFoundException(CustomUserNotFoundException ex) {
        return CommonResponse.builder()
                .responseCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .responseMessage(ex.getMessage())
                .build();
    }

    @ExceptionHandler(UserPersistException.class)
    public ResponseEntity<CommonResponse> userPersistException(UserPersistException ex) {
        CommonResponse commonResponse = CommonResponse.builder()
                .responseCode(ex.responseCode)
                .responseMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(commonResponse, HttpStatus.valueOf(Integer.parseInt(ex.responseCode)));
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<CommonResponse> userNotActiveException(UserNotActiveException ex) {
        CommonResponse commonResponse = CommonResponse.builder()
                .responseCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .responseMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(commonResponse, HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }

}
