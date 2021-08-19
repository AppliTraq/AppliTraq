package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Timeline;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.TimelineRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TimelineController {
    private final TimelineRepository timelineDao;
    private final JobApplicationRepository jobApplicationDao;

    public TimelineController(TimelineRepository timelineDao,  JobApplicationRepository jobApplicationDao) {
        this.timelineDao = timelineDao;
        this.jobApplicationDao = jobApplicationDao;
    }

    @GetMapping("/timeline")
    public String viewAppsOnTimeline(Model model) {
//  USED A CUSTOM METHOD FROM JOBAPPS REPOSITORY TO FIND JOB APPS BY USER ID
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("jobs", jobApplicationDao.findJobApplicationsByUserId(currentUser.getId()));
        return "myTimeline/index";
    }

   /* @PostMapping("/timeline")
    public String updateTimelineUponKanbanStatusChange(@RequestParam(name = "kanban_status") int kanbanStatus,
                                                       @RequestParam(name = "jobID")JobApplication jopApp) {
       List<Timeline> updatedTimelineStatus = timelineDao.findTimelinesByJobApplications(jopApp);
        return "redirect:/timeline";
    }*/

}
