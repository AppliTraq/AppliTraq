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
       /* model.addAttribute("kanbanStatus", timelineDao.findAll());*/
        List<Timeline> listOfTimelineStatuses =
                timelineDao.findTimelinesByJobApplications(jobApplicationDao.findById(currentUser.getId()));
        model.addAttribute("timelineStatuses", listOfTimelineStatuses);

        List<Timeline> listOfStatusesAt1 = new ArrayList<>();
        List<Timeline> listOfStatusesAt2 = new ArrayList<>();
        List<Timeline> listOfStatusesAt3 = new ArrayList<>();
        List<Timeline> listOfStatusesAt4 = new ArrayList<>();

        List<Timeline> onlyLastStatus = new ArrayList<>();

        for (Timeline status : listOfTimelineStatuses) {
            List<Timeline> allTimelineStatuses =
                    timelineDao.findTimelinesByJobApplications(status.getJobApplications());

            for (Timeline timeline : allTimelineStatuses) {
                if (timeline == allTimelineStatuses.get(allTimelineStatuses.size() - 1)) {
                    onlyLastStatus.add(timeline);
                }
            }
            for (Timeline lastTimeline : onlyLastStatus) {
                System.out.println("timeline id of onlyStatus list " + lastTimeline.getTimeline_id());

            }
            for (Timeline timeline : onlyLastStatus) {
                if (timeline.getKanbanStatus() == 1) {
                    listOfStatusesAt1.add(timeline);
                } else if (timeline.getKanbanStatus() == 2) {
                    listOfStatusesAt2.add(timeline);
                } else if (timeline.getKanbanStatus() == 3){
                    listOfStatusesAt3.add(timeline);
                } else if (timeline.getKanbanStatus() == 4) {
                    listOfStatusesAt4.add(timeline);
                }
            }
            model.addAttribute("status1", listOfStatusesAt1);
            model.addAttribute("status2", listOfStatusesAt2);
            model.addAttribute("status3", listOfStatusesAt3);
            model.addAttribute("status4", listOfStatusesAt4);
        }

        return "myTimeline/index";

    }
}
