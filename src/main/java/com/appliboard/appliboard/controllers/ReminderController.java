package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.Reminder;
import com.appliboard.appliboard.repositories.ReminderRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReminderController {
    private final ReminderRepository reminderDao;
    private final UserRepository userDao;

    public ReminderController(ReminderRepository reminderDao, UserRepository userDao) {
        this.reminderDao = reminderDao;
        this.userDao = userDao;
    }

    @GetMapping("/reminders/create")
    public String createReminder(Model model) {
        model.addAttribute("reminder", new Reminder());
        return "/reminders/create";
    }
}
