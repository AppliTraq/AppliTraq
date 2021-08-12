package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class SearchController {

    private final JobApplicationRepository jobApplicationDao;

    public SearchController(JobApplicationRepository jobApplicationDao) {
        this.jobApplicationDao = jobApplicationDao;
    }

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
