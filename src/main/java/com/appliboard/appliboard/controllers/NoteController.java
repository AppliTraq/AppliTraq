package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Note;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.JobApplicationRepository;
import com.appliboard.appliboard.repositories.NoteRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Controller
public class NoteController {
    private final NoteRepository noteDao;
    private final UserRepository userDao;
    private final JobApplicationRepository jobApplicationDao;

    public NoteController(NoteRepository noteDao, UserRepository userDao, JobApplicationRepository jobApplicationDao) {
        this.noteDao = noteDao;
        this.userDao = userDao;
        this.jobApplicationDao = jobApplicationDao;
    }

    @GetMapping("/notes/{jobId}/create")
    public String createNoteForm(Model model, @PathVariable long jobId){
        model.addAttribute("jobApp", jobId);
        model.addAttribute("note", new Note());
        return "/notes/create";
    }

    @PostMapping("/notes/{jobId}/create")
    public String createNote(@PathVariable long jobId, @ModelAttribute Note note, @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") Date fromDate){
        note.setJobApplication(jobApplicationDao.findById(jobId));
        note.setDate(Date.from(Instant.now()));
        noteDao.save(note);
        return "redirect:/jobApplications/" + jobId;
    }

    @GetMapping("/notes/{id}/edit/{jobId}")
    public String editNote (@PathVariable long id, @PathVariable long jobId, Model model){
        model.addAttribute("jobId", jobId);
        model.addAttribute("note", noteDao.findById(id));
        System.out.println("the id in the get mapping is " + id);
        return "/notes/edit";
    }

//original method that works but forces the id to be 1
    @PostMapping("/notes/{id}/edit/{jobId}")
    public String saveNote (@PathVariable long id, @PathVariable long jobId, @ModelAttribute Note note) {
        System.out.println("The Id that is being set for the note is " + id);
        note.setId(id);
        note.setJobApplication(jobApplicationDao.findById(jobId));
        note.setDate(Date.from(Instant.now()));
//        note.setTitle(note.getTitle());
//        note.setContent(note.getContent());
        noteDao.save(note);
        return "redirect:/jobApplications/" + jobId;
    }


    @GetMapping("/notes/index")
    public String viewNotes(Model model) {
        model.addAttribute("notes", noteDao.findAll());
        return "/notes/index";
    }

    @GetMapping("/notes/index/{id}")
    public String viewNotesByJobApp(@PathVariable long id, Model model) {
        model.addAttribute("job", jobApplicationDao.findById(id));
        model.addAttribute("note", noteDao.findNotesByJobApplicationId(id));
        return "/notes/index";
    }

    @PostMapping("/notes/{id}/delete/{jobId}")
    public String deleteNote(@PathVariable long id, @PathVariable long jobId){
        System.out.println("The ID of the note that is about to be deleted is "+ id);

        noteDao.deleteById(id);
        return "redirect:/jobApplications/" + jobId;
    }

}
