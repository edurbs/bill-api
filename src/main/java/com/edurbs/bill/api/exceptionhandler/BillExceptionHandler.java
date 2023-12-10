package com.edurbs.bill.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class BillExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

     @Override
     protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
             HttpHeaders headers, HttpStatus status, WebRequest request) {
         
        String userMessage = messageSource.getMessage("mensagem.invalida",null,LocaleContextHolder.getLocale());
        String debugMessage = ex.getCause().toString();
        Error body = new Error(userMessage, debugMessage);
         
        var myStatus = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(ex, body, headers, myStatus, request);
     }

     @Data
     @AllArgsConstructor
     public static class Error {
        private String userMessage;
        private String debugMessage;
     }



}
