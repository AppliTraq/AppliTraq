package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PasswordController {

    private UserRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public PasswordController (UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/password")
    public String showPasswordForm (Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);
        return "users/password";
    }

    @PostMapping("/password")
    public String saveNewPassword (@ModelAttribute User user){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // getting current user
        User userFromDb = usersDao.getById(currentUser.getId()); // capturing that value (copying?)
        String hash = passwordEncoder.encode(user.getPassword()); // capturing new password
        userFromDb.setPassword(hash); // setting captured password to user's table
        usersDao.save(userFromDb); // saving user details (copy)
        currentUser.setPassword(hash); // setting new hashed password to current suer (not copy) tf is going on here
        return "redirect:/login";
    }
}
