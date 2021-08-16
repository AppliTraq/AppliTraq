package com.appliboard.appliboard.controllers;

import com.appliboard.appliboard.repositories.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModalController {
    private final NoteRepository noteDao;
//messed with the mappings, i can habe the webpage properly load whenever the th:block is commented out
//    so its all still starting from there. nothing needed in security config to work.
    public ModalController(NoteRepository noteDao){
        this.noteDao = noteDao;
    }

    @GetMapping("/modals/TestModals")
    public String testModals(Model model) {
        model.addAttribute("notes", noteDao.findAll());
        return "/modals/TestModals";
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
