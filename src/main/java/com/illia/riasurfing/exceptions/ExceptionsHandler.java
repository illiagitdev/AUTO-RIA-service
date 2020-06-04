package com.illia.riasurfing.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(ExceptionHandler.class);
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @ExceptionHandler({UserEmailExistsException.class, UserNicknameExistsException.class})
    public ResponseEntity<Object> handleCustomExceptions(Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add(String.format("error: %s", ex.getLocalizedMessage()));
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        LOG.error(String.format("handleCustomExceptions: %s", objectMapper.writeValueAsString(apiError)));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @SneakyThrows
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(Exception ex) {

        String message;
        Throwable cause, resultCause = ex;
        while ((cause = resultCause.getCause()) != null && resultCause != cause) {
            resultCause = cause;
        }
        if (resultCause instanceof ConstraintViolationException) {
            message = (((ConstraintViolationException) resultCause).getConstraintViolations()).iterator().next().getMessage();
        } else {
            resultCause.printStackTrace();
            message = "Unknown error";
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, List.of());
        LOG.error(String.format("handleConstraintViolationException: %s", objectMapper.writeValueAsString(apiError)));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        LOG.error(String.format("handleMethodArgumentNotValid: %s", objectMapper.writeValueAsString(apiError)));
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }
}
