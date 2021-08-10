package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.models.Note;
import com.appliboard.appliboard.repositories.NoteRepository;
import com.appliboard.appliboard.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/notes/create")
    public String createNote(@ModelAttribute Note note){
        note.setNote_id(noteDao.getById(1));
        noteDao.save(note);
        return "/notes/index";
    }

    @GetMapping("/notes/index")
    public String index(Model model) {
        model.addAttribute("notes", noteDao.findAll());
        return "/notes/index";
    }

    @PostMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable long id){
        noteDao.delete(noteDao.findById(id));
        return "redirect:/notes/ads";
    }

}
