package com.illia.riasurfing.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such user")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException {

        List<String> errors = new ArrayList<>();
        errors.add(String.format("error: %s", exception.getLocalizedMessage()));
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(), errors);

        response.setStatus(apiError.getStatus().value());

        response.getOutputStream()
                .println(objectMapper.writeValueAsString(apiError));
    }
}
