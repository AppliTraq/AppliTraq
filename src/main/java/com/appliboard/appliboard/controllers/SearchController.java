package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    private final JobApplicationRepository jobApplicationDao;
    private final NoteRepository noteDao;

    public SearchController(JobApplicationRepository jobApplicationDao, NoteRepository noteDao) {
        this.jobApplicationDao = jobApplicationDao;
        this.noteDao = noteDao;
    }

    @GetMapping("/search")
    public String searchJobs(@RequestParam String search, Model model) {
        model.addAttribute("jobs", jobApplicationDao.findAllQuery(search));
        model.addAttribute("notes", noteDao.findAllQuery(search));
        return "/results";
    }

}
