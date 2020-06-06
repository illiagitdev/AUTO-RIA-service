package com.illia.riasurfing.controller;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{nickname}")
    public ResponseEntity<?> showDetails(@PathVariable("nickname") String nickname) {
        return ResponseEntity.ok(userService.getUser(nickname));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String principalName = principal.getUsername();
        userService.delete(userService.getUser(principalName).getId());
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User deleted");
    }

    @SneakyThrows
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User jsonUser) {
        userService.update(jsonUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User updated");
    }
}
