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

//    TODO continue solving the issue with the update which seems to stem from the id within here and the notes/edit html with the form id
    // TODO solve the issue with the mapping and why its showing as it is when you click on the edit button in the notes index
//

    @GetMapping("/notes/{id}/edit")
    public String editNote (@PathVariable long id, Model model){
      //  Note note = (Note) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // noteDao.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("note", noteDao.findById(id));
        return "/notes/edit";
    }

    @PostMapping("/notes/{id}/edit")
    public String saveNote (@PathVariable long id, @ModelAttribute Note note, @RequestParam String date) throws ParseException {
       // JobApplication jobApp = jobApplicationDao.getById(id);
        Note noteFromDB = noteDao.findById(id);
//        note.setNote_id(id);
//        note.setJobApplication(jobApplicationDao.findById(1));
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
//        Date formattedDate = (Date)formatter.parse(date);
        Date javaDate = new Date(date);
        noteFromDB.setDate(javaDate);

        noteFromDB.setTitle(note.getTitle());
        noteFromDB.setContent(note.getContent());
        noteDao.save(noteFromDB);

        return "redirect:/notes/index";
    }

    @GetMapping("/notes/index")
    public String viewNotes(Model model) {
        model.addAttribute("notes", noteDao.findAll());
        return "/notes/index";
    }

    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable long id){
        noteDao.deleteById(id);
        return "redirect:/notes/index";
    }

}
