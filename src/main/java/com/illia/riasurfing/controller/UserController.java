package com.illia.riasurfing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.dao.CustomRequestRepository;
import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.search.request.CustomRequest;
import com.illia.riasurfing.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;
    private CustomRequestRepository requestRepository;
    private ObjectMapper mapper;

    @Autowired
    public UserController(UserService userService, CustomRequestRepository requestRepository) {
        this.userService = userService;
        this.requestRepository = requestRepository;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping(path = "/get")
    public ResponseEntity<?> showDetails() {
        return ResponseEntity.ok(userService.getUser(getUserId()));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        final int userId = getUserId();
        userService.delete(userId);
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User deleted");
    }

    @SneakyThrows
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User jsonUser) {
        userService.update(jsonUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User updated");
    }

    @SneakyThrows
    @GetMapping(path = "/searchHistory")
    public ResponseEntity<?> getSearchHistory() {
        List<CustomRequest> requests = requestRepository.getAllByUserId(getUserId());
        return ResponseEntity.ok(mapper.writeValueAsString(requests));
    }

    private int getUserId() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String principalName = principal.getUsername();
        return userService.getUser(principalName).getId();
    }
}
