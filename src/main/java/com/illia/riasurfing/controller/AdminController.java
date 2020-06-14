package com.illia.riasurfing.controller;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.UserRole;
import com.illia.riasurfing.entities.UserStatus;
import com.illia.riasurfing.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Admin resource", description = "Admin level resources")
@RequestMapping(path = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Return list of all users")
    @GetMapping(value = "/users")
    public ResponseEntity<?> listUsers() {
        return  ResponseEntity.ok(userService.getList());
    }

    @ApiOperation(value = "Return list of users with status NEW")
    @GetMapping(value = "/usersNew")
    public ResponseEntity<?> listNewUsers() {
        return ResponseEntity.ok(userService.getListNew());
    }

    @ApiOperation(value = "Return list of users with status ACTIVE")
    @GetMapping(value = "/usersActive")
    public ResponseEntity<?> listActiveUsers() {
        return ResponseEntity.ok(userService.getListActive());
    }

    @ApiOperation(value = "Return list of users with status DISABLED")
    @GetMapping(value = "/usersDisabled")
    public ResponseEntity<?> listDisabledUsers() {
        return ResponseEntity.ok(userService.getListDisabled());
    }

    @ApiOperation(value = "Return user with {id}")
    @GetMapping(value = "/details/{id}")
    public ResponseEntity<?> showDetails(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @ApiOperation(value = "Accept {json} and update all user details! Return user")
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateUser(@RequestBody User jsonUser) {
        return ResponseEntity.ok(userService.updateAll(jsonUser));
    }

    @ApiOperation(value = "Update role for user with {id}. Return user")
    @PatchMapping(path = "/updateRole/{id}")
    public ResponseEntity<?> updateUserRole(@PathVariable("id") Integer id, @RequestParam("role") String role) {
        final User user = userService.updateRole(id, UserRole.valueOf(role));
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Update status for user with {id}. Return user")
    @PatchMapping(path = "/updateStatus/{id}")
    public ResponseEntity<?> updateUserStatus(@PathVariable("id") Integer id, @RequestParam("status") String status) {
        final User user = userService.updateStatus(id, UserStatus.valueOf(status));
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Delete user with {id}")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final int principalId = userService.getUser(principal.getUsername()).getId();
        userService.delete(id);
        if (principalId == id) {
            SecurityContextHolder.clearContext();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } else {
            return ResponseEntity.ok("User deleted");
        }
    }
}
