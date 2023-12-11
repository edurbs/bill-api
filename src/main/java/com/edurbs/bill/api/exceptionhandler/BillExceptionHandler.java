package com.edurbs.bill.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
      String message = "mensage.invalid";      
      return handleExceptionInternal(ex, generateBodyWithAnError(ex, message), headers, HttpStatus.BAD_REQUEST, request);
   }

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {      
      return handleExceptionInternal(ex, createBodyWithErrorsList(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
   }

   @ExceptionHandler({ EmptyResultDataAccessException.class })
   public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
         WebRequest request) {      
      String message = "resource.not-found";      
      return handleExceptionInternal(ex, generateBodyWithAnError(ex, message), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
   }

   @ExceptionHandler({InvalidDataAccessApiUsageException.class})
   public ResponseEntity<Object> handeInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, WebRequest request){
      String message = "resource.invalid";
      return handleExceptionInternal(ex, generateBodyWithAnError(ex, message), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
   }

   @ExceptionHandler({ DataIntegrityViolationException.class })
   public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
         WebRequest request) {      
      String message = "resource.operation-not-allowed";
      return handleExceptionInternal(ex, generateBodyWithAnError(ex, message), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
   }

   private List<Error> createBodyWithErrorsList(BindingResult bindingResult) {
      List<Error> errors = new ArrayList<>();
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
         var userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
         var debugMessage = fieldError.toString();
         errors.add(new Error(userMessage, debugMessage));
      }
      return errors;
   }

   private List<Error> generateBodyWithAnError(RuntimeException ex, String message) {
      String userMessage = messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
      String debugMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
      List<Error> body = Arrays.asList(new Error(userMessage, debugMessage));
      return body;
   }


   @Data
   @AllArgsConstructor
   public static class Error {
      private String userMessage;
      private String debugMessage;
   }

}
