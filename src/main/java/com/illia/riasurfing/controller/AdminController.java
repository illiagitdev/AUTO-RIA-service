package com.illia.riasurfing.controller;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.UserRole;
import com.illia.riasurfing.entities.UserStatus;
import com.illia.riasurfing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getList());
        return "user/list";
    }

    @GetMapping(value = "/usersNew")
    public String listNewUsers(Model model) {
        model.addAttribute("users", userService.getListNew());
        return "user/list";
    }

    @GetMapping(value = "/usersActive")
    public String listActiveUsers(Model model) {
        model.addAttribute("users", userService.getListActive());
        return "user/list";
    }

    @GetMapping(value = "/usersDisabled")
    public String listDisabledUsers(Model model) {
        model.addAttribute("users", userService.getListDisabled());
        return "user/list";
    }

    @GetMapping(value = "/details/{id}")
    public String showDetails(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user/details";
    }

    @GetMapping(path = "/updateRole/{id}")
    public String updateUserRoleView(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("roles", List.of(UserRole.values()));
        model.addAttribute("user", userService.getUser(id));
        return "user/update-role";
    }

    @PostMapping(path = "/updateRole/{id}")
    public String updateUserRole(@PathVariable("id") Integer id, @RequestParam("role") String role, Model model) {
        final User user = userService.updateRole(id, UserRole.valueOf(role));
        model.addAttribute("user", user);
        return "user/details";
    }

    @GetMapping(path = "/updateStatus/{id}")
    public String updateUserStatusView(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("statuses", List.of(UserStatus.values()));
        model.addAttribute("user", userService.getUser(id));
        return "user/update-status";
    }

    @PostMapping(path = "/updateStatus/{id}")
    public String updateUserStatus(@PathVariable("id") Integer id, @RequestParam("status") String status, Model model) {
        final User user = userService.updateStatus(id, UserStatus.valueOf(status));
        model.addAttribute("user", user);
        return "user/details";
    }
}
