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
//finally got it working, added this to the jobapps/show file since its gonna be the main modal
    @PostMapping("/notes/{id}/create")
    public String createNote(@PathVariable long id, @ModelAttribute Note note, @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") Date fromDate){
        note.setJobApplication(jobApplicationDao.findById(id));
        note.setDate(fromDate);
        noteDao.save(note);
        return "redirect:/jobApplications/" + id;
    }

    @GetMapping("/notes/{id}/edit/{jobId}")
    public String editNote (@PathVariable long id, @PathVariable long jobId, Model model){
        JobApplication jobApp = jobApplicationDao.getById(jobId);
      //  Note note = (Note) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // noteDao.findById(id);
//        possibly need to have the jobapp id go thru the path variable as well? then plug it in below like in the post mapping
//        model.addAttribute("jobApp", jobApplicationDao.findById(1));
        model.addAttribute("id", id);
        model.addAttribute("jobId", jobId);
        model.addAttribute("jobApp", jobApp);
        model.addAttribute("note", noteDao.findById(id));
        return "/notes/edit";
    }

    @PostMapping("/notes/{id}/edit/{jobApp}")
    public String saveNote (@ModelAttribute long id, @ModelAttribute long jobApp, @ModelAttribute Note note) {
        System.out.println(id);
        note.setNote_id(id);
        note.setJobApplication(jobApplicationDao.findById(jobApp));
        note.setDate(Date.from(Instant.now()));
        note.setTitle(note.getTitle());
        note.setContent(note.getContent());
        noteDao.save(note);
        return "redirect:/jobApplications/" + jobApp;
    }

    @GetMapping("/notes/index")
    public String viewNotes(Model model) {

        model.addAttribute("notes", noteDao.findAll());
        return "/notes/index";
    }

    @GetMapping("/notes/index/{id}")
    public String viewNotesByJobApp(@PathVariable long id, Model model) {
        model.addAttribute("job", jobApplicationDao.findById(id));

//        model.addAttribute("id", jobApplicationDao.findById(id));
// TODO       USE THIS! This should be the way to grab the needed notes by job app id, its been inside the note repo, will most likely need to change the notes/index so it only shows by job id which is what this method is for
        model.addAttribute("note", noteDao.findNotesByJobApplicationId(id));
        return "/notes/index";
    }

    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable long id){
        noteDao.deleteById(id);
        return "redirect:/notes/index";
    }

}
