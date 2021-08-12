package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class ProfileController {


    private UserRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public ProfileController (UserRepository users, PasswordEncoder passwordEncoder) {
        this.usersDao = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String showProfilePage (Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);
        return "/users/profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String takeToEditProfileForm (@PathVariable long id, Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);
        model.addAttribute("id", id);
        return "/users/edit";
    }

    @PostMapping("/profile/{id}/edit")
    public String saveEditsOnProfile (@PathVariable long id, Model model, @ModelAttribute User user){
        User userFromDb = usersDao.getById(id);
        user.setId(id);
        user.setPassword(userFromDb.getPassword());
        user.setGender(userFromDb.getGender());
        usersDao.save(user);

//      User details code updates the new saved user information into user details for the front end to have access to it
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        userDetails.setUsername(user.getUsername());
        userDetails.setEmail(user.getEmail());
        userDetails.setPassword(user.getPassword());
        userDetails.setGender(user.getGender());
        return "redirect:/profile";
    }
}
