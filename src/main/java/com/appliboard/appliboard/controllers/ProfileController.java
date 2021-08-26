package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Note;
import com.appliboard.appliboard.models.Reminder;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.NoteRepository;
import com.appliboard.appliboard.repositories.ReminderRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

@Controller
public class ProfileController {

    private UserRepository usersDao;
    private PasswordEncoder passwordEncoder;
    private JobApplicationRepository jobApplicationsDao;
    private NoteRepository noteDao;
    private ReminderRepository reminderDao;

    public ProfileController (UserRepository users, PasswordEncoder passwordEncoder, JobApplicationRepository jobApplicationsDao, NoteRepository noteDao, ReminderRepository reminderDao) {
        this.usersDao = users;
        this.passwordEncoder = passwordEncoder;
        this.jobApplicationsDao = jobApplicationsDao;
        this.noteDao = noteDao;
        this.reminderDao = reminderDao;
    }

    @GetMapping("/profile")
    public String showProfilePage (Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);
        List<JobApplication> jobs = jobApplicationsDao.findJobApplicationsByUserId(currentUser.getId());
        model.addAttribute("jobsNum", jobs.size());

        int counter = 0;
        for (int i = 0; i < jobs.size(); i++) {
            List<Note> notes = noteDao.findNotesByJobApplicationId(jobs.get(i).getId()); // getting one job at a time and getting all notes for it
            counter += notes.size(); // adding the amount of notes to counter
        }
        model.addAttribute("notesNum", counter);
        int reminderCounter = 0;
        for (int i = 0; i < jobs.size(); i++) {
            List<Reminder> reminders = reminderDao.findRemindersByJobApplication_Id(jobs.get(i).getId());
            reminderCounter += reminders.size();
        model.addAttribute("reminderNum", reminderCounter);
        }
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
    public String saveEditsOnProfile (@PathVariable long id, @ModelAttribute User user){
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
        userDetails.setAge(user.getAge());
        userDetails.setLocation(user.getLocation());
        return "redirect:/profile";
    }
}
