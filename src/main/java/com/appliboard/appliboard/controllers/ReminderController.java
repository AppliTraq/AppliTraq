package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.Reminder;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.ReminderRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class ReminderController {
    private final ReminderRepository reminderDao;
    private final UserRepository userDao;
    private final JobApplicationRepository jobApplicationDao;

    public ReminderController(ReminderRepository reminderDao, UserRepository userDao, JobApplicationRepository jobApplicationDao) {
        this.reminderDao = reminderDao;
        this.userDao = userDao;
        this.jobApplicationDao = jobApplicationDao;
    }

    // Form
    @GetMapping("/reminders/create")
    public String createReminderForm(Model model) {
        model.addAttribute("reminder", new Reminder());
        return "/reminders/create";
    }

    // creates the reminder
    @PostMapping("/reminders/create")
    public String createReminder(@ModelAttribute Reminder reminder) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reminder.setJobApplication(jobApplicationDao.findById(1));
        // pretty sure there is more missing...can't think anymore
        reminderDao.save(reminder);
        return "redirect: /reminders/index";
    }

//    TODO: post/get mapping for create, edit, delete, etc.

    // TODO: edit goes here

    // TODO: delete goes here
}
