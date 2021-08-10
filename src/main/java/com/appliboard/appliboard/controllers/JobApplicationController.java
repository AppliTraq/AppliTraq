package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobApplicationController {
    private final JobApplicationRepository jobApplicationDao;

    public JobApplicationController(JobApplicationRepository jobApplicationDao) {
        this.jobApplicationDao = jobApplicationDao;
    }
//jobs or jobsapps?

//    VIEW ALL JOBS
    @GetMapping("/jobApplications")
    public String viewJobs(Model model) {
        model.addAttribute("jobs", jobApplicationDao.findAll());
        return "jobApplications/index";
    }

//    CREATE A JOB, modal???? new page? which will it be? I took out the security code
    @GetMapping("/jobApplications/create")
    public String showCreateForm(Model model) {
        model.addAttribute("job", new JobApplication());
        return "jobApplications/create";
    }

    @PostMapping("/jobApplications/create")
    public String create(@ModelAttribute JobApplication jobApp) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        jobApp.setUser(user);
        jobApplicationDao.save(jobApp);
        return "redirect:/jobApplications/";
    }

//     EDITING, i kept the security code
    @GetMapping("/jobApplications/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JobApplication jobApp = jobApplicationDao.getById(id);
//        if (currentUser.getId() == jobApp.getUser().getId()) {
            model.addAttribute("jobApp", jobApp);
            return "jobApplications/edit";
//        } else {
//            return "redirect:?/jobApplications/" + id;
//        }
    }

    @PostMapping("/jobApplications/{id}/edit")
    public String editJob(@PathVariable long id, @ModelAttribute JobApplication jobApp) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JobApplication jobFromDB = jobApplicationDao.getById(id);
//        if (user.getId() == jobFromDB.getUser().getId()) {
//            jobApp.setUser(user);
            jobApplicationDao.save(jobApp);
//        }
        return "redirect:/jobApplications/" + id;
    }

//    DELETE
    @PostMapping("/jobApplications/{id}/delete")
    public String deleteJob(@PathVariable long id) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JobApplication jobApp = jobApplicationDao.getById(id);
//        if (currentUser.getId() == jobApp.getUser().getId()) {
            jobApplicationDao.delete(jobApp);
//        }
        return "redirect:/jobApplications";
    }

//    SEARCH
    @PostMapping("/search/{term}")
    public String search(Model model, @RequestParam(value = "term") String term) {
        List<JobApplication> searchresults = null;
        for (JobApplication result : jobApplicationDao.findAll()) {
            if (result.getTitle().contains(term)) {
                searchresults.add(result);
            }
        }
        model.addAttribute("results", searchresults);
        return "jobApplications/results";
    }
}
