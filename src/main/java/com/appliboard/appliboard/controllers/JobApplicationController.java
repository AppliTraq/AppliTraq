package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobApplicationController {
    private final JobApplicationRepository jobApplicationDao;

    public JobApplicationController(JobApplicationRepository jobApplicationDao) {
        this.jobApplicationDao = jobApplicationDao;
    }

//    VIEW ALL JOBS
    @GetMapping("/jobApplications")
    public String viewJobs(Model model) {

// CURRENTLY TESTING THIS, IT REDIRECTS BUT SERVER STILL HOLDS ON TO CACHE

        model.addAttribute("jobs", jobApplicationDao.findAll());
        return "jobApplications/index";


    }

//    VIEW SINGLE POST
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
//        TODO this still shows all posts as opposed to just ones owned by the users!
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

//     EDIT
    @GetMapping("/jobApplications/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JobApplication jobApp = jobApplicationDao.getById(id);
        if (currentUser.getId() == jobApp.getUser().getId()) {
            model.addAttribute("jobApp", jobApp);
            return "jobApplications/edit";
        } else {
            return "redirect:?/jobApplications/" + id;
        }
    }

    @PostMapping("/jobApplications/{id}/edit")
    public String editJob(@PathVariable long id, @ModelAttribute JobApplication jobApp) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JobApplication jobFromDB = jobApplicationDao.getById(id);
        if (user.getId() == jobFromDB.getUser().getId()) {
            jobApp.setUser(user);
            jobApplicationDao.save(jobApp);
        }
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

}
