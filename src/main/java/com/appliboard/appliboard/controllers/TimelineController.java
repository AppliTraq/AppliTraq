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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
        List<JobApplication> listOfJobs = jobApplicationDao.findJobApplicationsByUserId(currentUser.getId());

        List<Timeline> listOfStatusAt1 = new ArrayList<>();
        List<Timeline> listOfStatusAt2 = new ArrayList<>();
        List<Timeline> listOfStatusAt3 = new ArrayList<>();
        List<Timeline> listOfStatusAt4 = new ArrayList<>();

        for (JobApplication job : listOfJobs) {
            List<Timeline> allTimelineStatuses = timelineDao.findTimelinesByJobApplications(job);

            int lastIndex = allTimelineStatuses.size() -1;
            System.out.println("Job ID " + job.getId());
            for (Timeline timeline : timelineDao.findTimelinesByJobApplications(job)) {
                System.out.println("timeline id" + timeline.getTimeline_id());
                System.out.println("timeline get kanban status: " + timeline.getKanbanStatus());
                if (timeline.getKanbanStatus() == 1) {
                    listOfStatusAt1.add(job);
                } else if (timeline.getKanbanStatus() == 2) {
                    listOfStatusAt2.add(job);
                } else if (timeline.getKanbanStatus() == 3){
                    listOfStatusAt3.add(job);
                } else if (timeline.getKanbanStatus() == 4) {
                    listOfStatusAt4.add(job);
                }
            }
        }
        model.addAttribute("status1", listOfStatusAt1);
        model.addAttribute("status2", listOfStatusAt2);
        model.addAttribute("status3", listOfStatusAt3);
        model.addAttribute("status4", listOfStatusAt4);
        return "myTimeline/index";

    }
}
