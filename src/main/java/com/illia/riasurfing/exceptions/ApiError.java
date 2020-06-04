package com.illia.riasurfing.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ApiError {

    private HttpStatus status;
    private String error;
    private List<String> errors;

    public ApiError(HttpStatus status, String error, List<String> errors) {
        this.status = status;
        this.error = error;
        this.errors = errors;
    }
}
