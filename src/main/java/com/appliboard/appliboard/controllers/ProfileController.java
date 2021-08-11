package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProfileController {

    UserRepository usersDao;

    @GetMapping("/profile")
    public String showProfilePage (Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);
        System.out.println(currentUser.getUsername());
        return "/users/profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String takeToEditProfileForm (@PathVariable long id, Model model){
        System.out.println(id);
        return "/users/edit";
    }
}
