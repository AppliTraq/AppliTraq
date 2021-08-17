package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Note;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.NoteRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@Controller
public class JobApplicationController {
    private final JobApplicationRepository jobApplicationDao;
    private final UserRepository usersDao;
    private final NoteRepository noteDao;

    public JobApplicationController(JobApplicationRepository jobApplicationDao, UserRepository usersDao, NoteRepository noteDao) {
        this.jobApplicationDao = jobApplicationDao;
        this.usersDao = usersDao;
        this.noteDao = noteDao;
    }

//    VIEW ALL
    @GetMapping("/jobApplications")
    public String viewJobs(Model model) {
//  USED A CUSTOM METHOD FROM JOBAPPS REPOSITORY TO FIND JOB APPS BY USER ID
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("jobs", jobApplicationDao.findJobApplicationsByUserId(currentUser.getId()));
        model.addAttribute("note", new Note());
//        model.addAttribute("notes", new Note());
        return "jobApplications/index";
    }
//tested to see if it was necessary, but doesnt seem like it. leaving here for reference will delete later
    @PostMapping("/jobApplications")
    public String createNote(Model model, @ModelAttribute Note note, @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") Date fromDate) {
        note.setJobApplication(jobApplicationDao.findById(1));
        note.setDate(fromDate);
        noteDao.save(note);
        return "redirect:/notes/index";
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
        model.addAttribute("isJobOwner", isJobOwner);
        model.addAttribute("notes", noteDao.findNotesByJobApplicationId(id));
        model.addAttribute("jobApp", jobApp);
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

//    @GetMapping("/notes/index")
//    public String jobsNotes(Model model) {
//        model.addAttribute("notes", noteDao.findAll());
//        return "/jobAppliations/index";
//    }

}
