package kh.edu.paragon.mission5.controller;

import kh.edu.paragon.mission5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private EventService eventService;

    // Homepage strictly retrieves data (Cache-first via Service)
    @GetMapping("/")
    public String viewHomePage(Model model) {
        // This method in the service will be annotated with @Cacheable
        model.addAttribute("events", eventService.getAllEvents());
        return "home";
    }
}