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
        List<JobApplication> listOfJobs = jobApplicationDao.findJobApplicationsByUserId(currentUser.getId());
        model.addAttribute("jobs", listOfJobs);
/*Below are the array lists for storing the jobs at each state on the kanban board*/
        List<JobApplication> listOfJobsAt1 = new ArrayList<>();
        List<JobApplication> listOfJobsAt2 = new ArrayList<>();
        List<JobApplication> listOfJobsAt3 = new ArrayList<>();
        List<JobApplication> listOfJobsAt4 = new ArrayList<>();

        List<Timeline> onlyLastStatusJobList = new ArrayList<>();

        /*Below are the array lists for sorting jobs by month*/

        List<Timeline> January = new ArrayList<>();
        List<Timeline> February = new ArrayList<>();
        List<Timeline> March = new ArrayList<>();
        List<Timeline> April = new ArrayList<>();
        List<Timeline> May = new ArrayList<>();
        List<Timeline> June = new ArrayList<>();
        List<Timeline> July = new ArrayList<>();
        List<Timeline> August = new ArrayList<>();
        List<Timeline> September = new ArrayList<>();
        List<Timeline> October = new ArrayList<>();
        List<Timeline> November = new ArrayList<>();
        List<Timeline> December = new ArrayList<>();

        for (JobApplication job : listOfJobs) {
            List<Timeline> allTimelineStatuses =
                    timelineDao.findTimelinesByJobApplications(job);

            for (Timeline timeline : allTimelineStatuses) {
                if (timeline == allTimelineStatuses.get(allTimelineStatuses.size() - 1)) {
                    onlyLastStatusJobList.add(timeline);
                }
            }
        }

        /*Create buckets for months*/

/*loop for organizing the statuses by date*/
        for ( Timeline timeline : onlyLastStatusJobList) {
            if (timeline) {
                January.add(timeline.getJobApplications());
            } else if (timeline) {
                February.add(timeline.getJobApplications());
            } else if (timeline) {
                March.add(timeline.getJobApplications());
            } else if (timeline) {
                April.add(timeline.getJobApplications());
            } else if (timeline) {
                May.add(timeline.getJobApplications());
            } else if (timeline) {
                June.add(timeline.getJobApplications());
            } else if (timeline) {
                July.add(timeline.getJobApplications());
            } else if (timeline) {
                August.add(timeline.getJobApplications());
            } else if (timeline) {
                September.add(timeline.getJobApplications());
            } else if (timeline) {
                October.add(timeline.getJobApplications());
            } else if (timeline) {
                November.add(timeline.getJobApplications());
            } else if (timeline) {
                April.add(timeline.getJobApplications());
            } else if (timeline) {
                December.add(timeline);
            }
        }

            for ( Timeline timeline : onlyLastStatusJobList) {
                System.out.println("timeline id: " + timeline.getTimeline_id());
                System.out.println("timeline get kanban status: " + timeline.getKanbanStatus());
                if (timeline.getKanbanStatus() == 1) {
                    listOfJobsAt1.add(timeline.getJobApplications());
                } else if (timeline.getKanbanStatus() == 2) {
                    listOfJobsAt2.add(timeline.getJobApplications());
                } else if (timeline.getKanbanStatus() == 3) {
                    listOfJobsAt3.add(timeline.getJobApplications());
                } else if (timeline.getKanbanStatus() == 4) {
                    listOfJobsAt4.add(timeline.getJobApplications());
                }
            }
            model.addAttribute("status1", listOfJobsAt1);
            model.addAttribute("status2", listOfJobsAt2);
            model.addAttribute("status3", listOfJobsAt3);
            model.addAttribute("status4", listOfJobsAt4);

            /*Display based on both factors: status and what month they are in, create new array lists to organize
            the list of jobs based on the month of the last timeline created (last status) - iterate through 4 lists
            of jobs to get the timelines by job applicatiosn (method in model) */
        return "myTimeline/index";

    }
}
