package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.Note;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.NoteRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
public class ModalController {
    private final NoteRepository noteDao;
    private final JobApplicationRepository jobApplicationDao;

    public ModalController(NoteRepository noteDao, JobApplicationRepository jobApplicationDao){
        this.noteDao = noteDao;
        this.jobApplicationDao = jobApplicationDao;
    }

    @GetMapping("modals/TestModals")
    public String testModals(Model model, @ModelAttribute Note note, @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") Date fromDate) {
        model.addAttribute("note", new Note());
       return"modals/TestModals";
    }

    @PostMapping("modals/TestModals")
    public String createNoteModal(Model model, @ModelAttribute Note note, @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") Date fromDate) {
        note.setJobApplication(jobApplicationDao.findById(1));
        noteDao.save(note);
        return "redirect:/notes/index";
    }

    @GetMapping("modal1")
    public String modal1(){
        return "modals/modal1";
    }

    @GetMapping("modal2")
    public String modal2(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "modals/modal2";
    }
}
