package com.ms.news.controller;

import com.ms.news.model.dto.response.ErrorResponse;
import com.ms.news.model.dto.response.enums.ErrorType;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice // esto indica que este clase va a manejar las excepciones de los controllers
public class GlobalControllerAdvice {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HandlerMethodValidationException.class)
  public ErrorResponse handleValidationException(HandlerMethodValidationException e) {
    log.error("Error: ", e);
    return ErrorResponse.builder()
        .message(e.getMessage())
        .errorType(ErrorType.FUNCTIONAL)
        .details(Collections.singletonList(e.getMessage()))
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("Error: ", e);
    BindingResult bindingResult = e.getBindingResult();
    return ErrorResponse.builder()
        .message(e.getMessage())
        .errorType(ErrorType.FUNCTIONAL)
        .details(
            bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList())
        .build();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorResponse handleException(Exception e) {
    log.error("Error: ", e);
    return ErrorResponse.builder()
        .message(e.getMessage())
        .errorType(ErrorType.SYSTEM)
        .details(Collections.singletonList(e.getMessage()))
        .build();
  }
}
