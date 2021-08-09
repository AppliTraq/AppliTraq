package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private UserRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController (UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterForm (Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

//    @GetMapping("/login")
//    public String takeToLogin (Model model);







}
