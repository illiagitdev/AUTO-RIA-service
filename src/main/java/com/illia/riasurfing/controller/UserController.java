package com.illia.riasurfing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.search.request.CustomRequest;
import com.illia.riasurfing.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;
    private ObjectMapper mapper;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<?> getSearchHistory(
            @PageableDefault(size = 50, sort = "id", direction = Sort.Direction.DESC) Pageable p) {
        Page<CustomRequest> page = userService.getSearchHistoryPage(getUserId(), p);
        return ResponseEntity.ok(mapper.writeValueAsString(page));
    }

    @PutMapping(path = "/subscribe")
    public ResponseEntity<?> getSubscribeToSearchUpdates(@RequestParam("requestId") Integer requestId) {
        userService.enableSubscription(requestId);
        return ResponseEntity.ok("Subscribed.");
    }

    @PutMapping(path = "/unsubscribe")
    public ResponseEntity<?> disableSubscribeToSearchUpdates(@RequestParam("requestId") Integer requestId) {
        userService.disableSubscription(requestId);
        return ResponseEntity.ok("Subscription disabled.");
    }

    private int getUserId() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String principalName = principal.getUsername();
        return userService.getUser(principalName).getId();
    }
}
