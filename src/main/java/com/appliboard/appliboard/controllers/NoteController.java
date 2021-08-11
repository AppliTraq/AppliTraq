package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.Note;
import com.appliboard.appliboard.models.User;
import com.appliboard.appliboard.repositories.NoteRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;

@Controller
public class NoteController {
    private final NoteRepository noteDao;
    private final UserRepository userDao;

    public NoteController(NoteRepository noteDao, UserRepository userDao) {
        this.noteDao = noteDao;
        this.userDao = userDao;
    }

    @GetMapping("/notes/create")
    public String createNoteForm(Model model){
        model.addAttribute("note", new Note());

        return "/notes/create";
    }

//    Need to either update the note.java file to auto update with the current time bc of the datetime column or insert it below. try the first ideally. then the job_id column in the
//            table is the jobapplication you see below, need to link that together with the job_app so it inserts into the note controller properly and updates

    @PostMapping("/notes/create")
    public String createNote(@ModelAttribute Note note){

        JobApplication jobApplication = (JobApplication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //figure out this to implement the timestamp or just do it automatically in some kinda auto increment way in MySQL
//        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
//        PreparedStatement.setTimestamp(1, date);

//        note.setJobApplication(jobApplication.getId(jobApplication));
            note.setJobApplication(jobApplication);
//        note.getJobApplication()
//        note.setNote_id(user.getId());
//        maybe try this way below as a test?
//        note.setNote_id(1);
        noteDao.save(note);
        return "/notes/index";
    }

    @GetMapping("/notes/index")
    public String viewNotes(Model model) {
        model.addAttribute("notes", noteDao.findAll());
        return "/notes/index";
    }

    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable long id){
//        noteDao.delete(noteDao.findById(id));
        noteDao.deleteById(id);
        return "redirect:/notes/index";
    }

}
