package com.appliboard.appliboard.controllers;
import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Reminder;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.ReminderRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import com.appliboard.appliboard.services.EmailService;;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Controller
public class ReminderController {
    private final ReminderRepository reminderDao;
    private final UserRepository userDao;
    private final JobApplicationRepository jobApplicationDao;
    private final EmailService emailService; // needs to be added in order for EmailService to work

    public ReminderController(ReminderRepository reminderDao, UserRepository userDao, JobApplicationRepository jobApplicationDao, EmailService emailService) {
        this.reminderDao = reminderDao;
        this.userDao = userDao;
        this.jobApplicationDao = jobApplicationDao;
        this.emailService = emailService;
    }

    // finds the reminders
    @GetMapping("/reminders/index")
    public String findReminders(Model model) {
        model.addAttribute("reminders", reminderDao.findAll());
        return "/reminders/index";
    }

    // shows all the reminders
    @GetMapping("/reminders/index/{id}")
    public String viewReminders(@PathVariable long id, Model model) {
        model.addAttribute("job", jobApplicationDao.findById(id));
        model.addAttribute("reminder", reminderDao.findRemindersByJobApplication_Id(id));
        return "/reminders/index";
    }

    // access to the create form
    @GetMapping("/reminders/{id}/create")
    public String createReminderForm(Model model, @PathVariable long id) {
        model.addAttribute("job", jobApplicationDao.findById(id));
        model.addAttribute("reminder", new Reminder());
        return "/reminders/create";
    }

    // posts the reminder
    @PostMapping("/reminders/{id}/create")
    public String createReminder(@ModelAttribute Reminder reminder, @ModelAttribute JobApplication jobApp, @PathVariable long id, BindingResult validationResult, @RequestParam String description) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reminder.setJobApplication(jobApp);
        reminder.setDescription(description);
        try {
            emailService.sendEmail(user, jobApp); // connected to the EmailService class
        } catch (IOException e) {
            e.printStackTrace();
        }
        reminderDao.save(reminder);
        return "redirect:/jobApplications/" + id;
    }

    @PostMapping("/reminders/{id}/createFromKanban")
    public String createReminderFromKanban(@ModelAttribute Reminder reminder, @ModelAttribute JobApplication jobApp, @PathVariable long id, BindingResult validationResult, @RequestParam String description) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reminder.setJobApplication(jobApp);
        reminder.setDescription(description);

        try {
            emailService.sendEmail(user, jobApp); // connected to the EmailService class
        } catch (IOException e) {
            e.printStackTrace();
        }
        reminderDao.save(reminder);
        return "redirect:/jobApplications/";
    }


    // allows the reminders to be edited
    @GetMapping("/reminders/{id}/edit/{jobId}")
    public String editReminder(@PathVariable long id, Model model, @PathVariable long jobId) {
        model.addAttribute("id", id);
        model.addAttribute("reminder", reminderDao.findById(id));
        model.addAttribute("jobId", jobId);
        return "/reminders/edit";
    }

    // posts/saves the edited reminders
    @PostMapping("/reminders/{id}/edit/{jobId}")
    public String postReminder(@PathVariable long id, @ModelAttribute Reminder reminder, @PathVariable long jobId) {
        reminder.setReminder_id(id);
        reminder.setJobApplication(jobApplicationDao.findById(jobId));
        reminder.setTitle(reminder.getTitle());
        reminder.setDescription(reminder.getDescription());
        reminderDao.save(reminder);
        return "redirect:/jobApplications/" + jobId;
    }

    // deletes a single reminder
    @PostMapping("/reminders/{id}/delete/{jobId}")
    public String deleteReminder(@PathVariable long id, @PathVariable long jobId) {
        reminderDao.deleteById(id);
        return "redirect:/jobApplications/" + jobId;
    }
}
