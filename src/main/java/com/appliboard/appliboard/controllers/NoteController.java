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

    @GetMapping("/notes/create")
    public String createNoteForm(Model model){
        model.addAttribute("note", new Note());
        return "/notes/create";
    }

    @PostMapping("/notes/create")
    public String createNote(@ModelAttribute Note note, @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") Date fromDate){
        note.setJobApplication(jobApplicationDao.findById(1));
        note.setDate(fromDate);
        noteDao.save(note);
        return "redirect:/notes/index";
    }

    @GetMapping("/notes/{id}/edit")
    public String editNote (@PathVariable long id, Model model){
      //  Note note = (Note) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // noteDao.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("note", noteDao.findById(id));
        return "/notes/edit";
    }

    @PostMapping("/notes/{id}/edit")
    public String saveNote (@PathVariable long id, @ModelAttribute Note note) {
        note.setNote_id(id);
        note.setJobApplication(jobApplicationDao.findById(1));
        note.setDate(Date.from(Instant.now()));
        note.setTitle(note.getTitle());
        note.setContent(note.getContent());
        noteDao.save(note);
        return "redirect:/notes/index";
    }

    @GetMapping("/notes/index")
    public String viewNotes(Model model) {
        model.addAttribute("notes", noteDao.findAll());
        return "/notes/index";
    }

    @GetMapping("/notes/index/{id}")
    public String viewNotesByJobApp(@PathVariable long id, Model model) {
//todo        possibly change this so it doesnt find by id, because right now there isnt a note id of 4 like the job app for google is for the user of admin
//                need to have it find the id by the job app id and have them all correlate and matching
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("job", jobApplicationDao.findById(id));

//        model.addAttribute("id", jobApplicationDao.findById(id));
        model.addAttribute("notes", noteDao.findById(id));
        return "/notes/index";
    }



    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable long id){
        noteDao.deleteById(id);
        return "redirect:/notes/index";
    }

}
