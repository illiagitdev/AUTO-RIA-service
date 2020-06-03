package com.illia.riasurfing.controller;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.exceptions.UserEmailExistsException;
import com.illia.riasurfing.exceptions.UserNicknameExistsException;
import com.illia.riasurfing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String userRegistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        try {
            userService.create(user);
        } catch (UserNicknameExistsException | UserEmailExistsException ex) {
            model.addAttribute("errors", ex.getMessage());
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping(path = "/details/{nickname}")
    public String showDetails(@PathVariable("nickname") String nickname, Model model) {
        model.addAttribute("user", userService.getUser(nickname));
        return "user/details";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final int principalId = userService.getUser(principal.getUsername()).getId();
        userService.delete(id);
        if (principalId == id) {
            SecurityContextHolder.clearContext();
            return "redirect:/login";
        } else {
            return "index";
        }
    }

    @ModelAttribute
    public User defaultUser() {
        return new User();
    }
}
