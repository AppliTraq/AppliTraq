//package com.appliboard.appliboard.controllers;
//
//import com.appliboard.appliboard.models.Reminder;
//import com.appliboard.appliboard.models.User;
//import com.appliboard.appliboard.repositories.JobApplicationRepository;
//import com.appliboard.appliboard.repositories.ReminderRepository;
//import com.appliboard.appliboard.repositories.UserRepository;
//import com.appliboard.appliboard.services.EmailService;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.Date;
//
//@Controller
//public class ReminderController {
//    private final ReminderRepository reminderDao;
//    private final UserRepository userDao;
//    private final JobApplicationRepository jobApplicationDao;
//    private final EmailService emailService; // needs to be added in order for EmailService to work
//
//    public ReminderController(ReminderRepository reminderDao, UserRepository userDao, JobApplicationRepository jobApplicationDao, EmailService emailService) {
//        this.reminderDao = reminderDao;
//        this.userDao = userDao;
//        this.jobApplicationDao = jobApplicationDao;
//        this.emailService = emailService;
//    }
//
//    // shows all the reminders
//    @GetMapping("/reminders/index")
//    public String viewReminders(Model model) {
//        model.addAttribute("reminders", reminderDao.findAll());
//        return "/reminders/index";
//    }
//
//    // access to the create form
//    @GetMapping("/reminders/create")
//    public String createReminderForm(Model model) {
//        model.addAttribute("reminder", new Reminder());
//        return "/reminders/create";
//    }
//
//    // creates the reminder
//    @PostMapping("/reminders/create")
//    public String createReminder(@ModelAttribute Reminder reminder) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        reminder.setJobApplication(jobApplicationDao.findById(1));
//        emailService.prepareAndSend(reminder, "REMINDER: " + reminder.getTitle(), reminder.getDescription()); // connected to the EmailService class
//        // pretty sure there is more missing...can't think anymore
//        reminderDao.save(reminder);
//        return "redirect:/reminders/index";
//    }
//
//    // allows the reminders to be edited
//    @GetMapping("/reminders/{id}/edit")
//    public String editReminder(@PathVariable long id, Model model) {
//        model.addAttribute("id", id);
//        model.addAttribute("reminder", reminderDao.findById(id));
//        return "/reminders/edit";
//    }
//
//    // posts/saves the edited reminders
//    @PostMapping("/reminders/{id}/edit")
//    public String postReminder(@PathVariable long id, @ModelAttribute Reminder reminder) {
//        reminder.setReminder_id(id);
//        reminder.setJobApplication(jobApplicationDao.findById(1));
//        reminder.setTitle(reminder.getTitle());
//        reminder.setDescription(reminder.getDescription());
//
//        reminderDao.save(reminder);
//        return "redirect:/reminders/index";
//    }
//
//    // deletes a single reminder
//    @PostMapping("/reminders/delete/{id}")
//    public String deleteReminder(@PathVariable long id) {
//        reminderDao.deleteById(id);
//        return "redirect:/reminders/index";
//    }
//
//}

/*
package com.appliboard.appliboard.controllers;
=======


