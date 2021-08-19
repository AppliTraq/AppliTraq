package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Note;
import com.appliboard.appliboard.models.Timeline;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.NoteRepository;
import com.appliboard.appliboard.repositories.TimelineRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Controller
public class JobApplicationController {
    private final JobApplicationRepository jobApplicationDao;
    private final UserRepository usersDao;
    private final NoteRepository noteDao;
    private final TimelineRepository timelineDao;

    public JobApplicationController(JobApplicationRepository jobApplicationDao, UserRepository usersDao, NoteRepository noteDao, TimelineRepository timelineDao) {
        this.jobApplicationDao = jobApplicationDao;
        this.usersDao = usersDao;
        this.noteDao = noteDao;
        this.timelineDao = timelineDao;
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
        Timeline timeline = new Timeline(jobApp, Date.from(Instant.now()), 1);
        List<Timeline> newListOfTimelineStatus = new ArrayList<Timeline>();
        newListOfTimelineStatus.add(timeline);
        jobApp.setTimeline(newListOfTimelineStatus);
        System.out.println(jobApp.getTimeline());
        jobApplicationDao.save(jobApp);
//        timelineDao.save(timeline);
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
    public String updateKanbanStatus(@RequestParam(name = "kanban_status") int kanbanStatus, @RequestParam(name = "jobId") List <Long> jobIds) {
        System.out.println(jobIds);
        int lastIndex = jobIds.size() - 1;
        JobApplication jobApp = jobApplicationDao.getById(jobIds.get(lastIndex));
        System.out.println("get timeline "  + jobApp.getTimeline());
        System.out.println( timelineDao.findTimelinesByJobApplications(jobApp.getId()));
        List<Timeline> timelineList = jobApp.getTimeline();

        System.out.println(jobApp.getId());
        System.out.println("This is kanban status" + kanbanStatus);
        System.out.println("This is size of timeline list" + timelineList.size());
        for (Timeline timeline : timelineList) {
            System.out.println(timeline.getJobApplications().getId());
//            if (jobApp.getId() == timeline.getJobApplications().getId()){
//                timeline.setKanban_status(kanbanStatus);
//                System.out.println(timeline.getKanban_status());
//            }
//            timelineDao.save(timeline);
        }
        System.out.println("This submit works");
        return "redirect:/jobApplications";
    }

//    @GetMapping("/notes/index")
//    public String jobsNotes(Model model) {
//        model.addAttribute("notes", noteDao.findAll());
//        return "/jobAppliations/index";
//    }

}
