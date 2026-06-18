package kh.edu.paragon.mission5.controller;

import kh.edu.paragon.mission5.model.Category;
import kh.edu.paragon.mission5.model.Event;
import kh.edu.paragon.mission5.service.CategoryService;
import kh.edu.paragon.mission5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CategoryService categoryService;

    // View Admin Dashboard
    @GetMapping
    public String viewAdminPanel(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "admin";
    }

    // --- EVENT MANAGEMENT ---

    @GetMapping("/events/new")
    public String showNewEventForm(Model model) {
        model.addAttribute("event", new Event());
        // Pass the dynamic list of categories to the form!
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin-form";
    }

    @PostMapping("/events")
    public String saveEvent(@ModelAttribute("event") Event event) {
        eventService.saveEvent(event);
        return "redirect:/admin";
    }

    // --- CATEGORY MANAGEMENT ---

    @GetMapping("/categories/new")
    public String showNewCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin-category-form";
    }

    @PostMapping("/categories")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin?categoryCreated=true"; // Returns to dashboard with success message
    }

    // --- CACHE MANAGEMENT ---

    @PostMapping("/cache/refresh")
    public String refreshCache() {
        eventService.refreshCache();
        return "redirect:/admin?cacheRefreshed=true";
    }
}