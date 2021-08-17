package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobApplicationController {
    private final JobApplicationRepository jobApplicationDao;
    private final UserRepository usersDao;

    public JobApplicationController(JobApplicationRepository jobApplicationDao, UserRepository usersDao) {
        this.jobApplicationDao = jobApplicationDao;
        this.usersDao = usersDao;
    }

//    VIEW ALL
    @GetMapping("/jobApplications")
    public String viewJobs(Model model) {
//  USED A CUSTOM METHOD FROM JOBAPPS REPOSITORY TO FIND JOB APPS BY USER ID
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("jobs", jobApplicationDao.findJobApplicationsByUserId(currentUser.getId()));
        return "jobApplications/index";
    }

//    VIEW SINGLE
    @GetMapping("/jobApplications/{id}")
    public String JobById(@PathVariable long id, Model model) {
        JobApplication jobApp = jobApplicationDao.getById(id);
        boolean isJobOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isJobOwner = currentUser.getId() == jobApp.getUser().getId();
        }
        model.addAttribute("jobApp", jobApp);
        model.addAttribute("isJobOwner", isJobOwner);
        return "jobApplications/show";
    }

//    CREATE
    @GetMapping("/jobApplications/create")
    public String showCreateForm(Model model) {
        model.addAttribute("job", new JobApplication());
        return "jobApplications/create";
    }

    @PostMapping("/jobApplications/create")
    public String create(@ModelAttribute JobApplication jobApp) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jobApp.setUser(user);
        jobApplicationDao.save(jobApp);
        return "redirect:/jobApplications/";
    }

//    EDIT
    @GetMapping("/jobApplications/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("jobApp", jobApplicationDao.findById(id));
        return "jobApplications/edit";
    }

    @PostMapping("/jobApplications/{id}/edit")
    public String editJobApp(@PathVariable long id, @ModelAttribute JobApplication jobApp) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jobApp.setUser(currentUser);
        jobApp.setId(id);
        jobApplicationDao.save(jobApp);
        return "redirect:/jobApplications/" + id;
    }

//    DELETE
    @PostMapping("/jobApplications/{id}/delete")
    public String deleteJob(@PathVariable long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JobApplication jobApp = jobApplicationDao.getById(id);
        if (currentUser.getId() == jobApp.getUser().getId()) {
            jobApplicationDao.delete(jobApp);
        }
        return "redirect:/jobApplications";
    }

//  UPDATE KANBAN TO STATUS
    @PostMapping("/jobApplications/kanban/update")
    public String updateKanbanStatus() {
        System.out.println("This submit works");
        return "redirect:/jobApplications";
    }

}
