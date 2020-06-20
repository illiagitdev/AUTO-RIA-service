package com.illia.riasurfing.controller;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Registration", description = "Endpoint for registration new users")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @ApiOperation(value = "Register new user, accept {json}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @PostMapping(path = "/registration")
    public ResponseEntity<?> userRegistration(@RequestBody User jsonUser) {
            userService.create(jsonUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }
}
