package com.apiclientes.exception;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorsResponse extends ResponseEntityExceptionHandler {

    final Message message = new Message();

    private final MessageSource messageSource;

    public ErrorsResponse(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Message.Fields> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = error.getObjectName();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fields.add(new Message.Fields(name, message));
        }

        message.setStatus(status.value());
        message.setTimestamp(OffsetDateTime.now());
        message.setMessage("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        message.setFields(fields);


        return handleExceptionInternal(ex, message, headers, status, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        message.setStatus(status.value());
        message.setTimestamp(OffsetDateTime.now());
        message.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, message, new HttpHeaders(), status, request);
    }

}
