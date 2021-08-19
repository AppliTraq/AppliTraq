//package com.appliboard.appliboard.controllers;
//import com.appliboard.appliboard.models.JobApplication;
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
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
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
//    @GetMapping("/reminders/create/{id}")
//    public String createReminderForm(Model model, @PathVariable long id) {
//        model.addAttribute("reminder", new Reminder());
//        model.addAttribute("job", jobApplicationDao.findById(id));
//        return "/reminders/create";
//    }
//
//    // creates the reminder
//    @PostMapping("/reminders/create/{id}")
//    public String createReminder(@ModelAttribute Reminder reminder, @RequestParam("reminderSelect") String reminderSelect, @ModelAttribute JobApplication jobApp, BindingResult validationResult) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(user.getEmail());
//        reminder.setJobApplication(jobApp);
//        System.out.println(jobApp.getTitle());
//        emailService.prepareAndSend(user, reminderSelect, jobApp); // connected to the EmailService class
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
//}
