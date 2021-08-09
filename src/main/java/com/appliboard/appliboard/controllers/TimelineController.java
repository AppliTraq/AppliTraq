package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.TimelineRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class TimelineController {
    private final TimelineRepository timelineDao;
    private final JobApplicationRepository jobApplicationDao;

    public TimelineController(TimelineRepository timelineDao,  JobApplicationRepository jobApplicationDao) {
        this.timelineDao = timelineDao;
        this.jobApplicationDao = jobApplicationDao;
    }

    @GetMapping("/timeline")
    public String timelineShow(Model model) {
        model.addAttribute("job", jobApplicationDao.findAll());
        return "users/timeline"; // might change?
    }

}
