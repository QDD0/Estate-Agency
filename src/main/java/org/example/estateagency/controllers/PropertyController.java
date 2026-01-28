package org.example.estateagency.controllers;

import jakarta.validation.Valid;
import org.example.estateagency.models.Properties;
import org.example.estateagency.services.PersonService;
import org.example.estateagency.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyService propertyService;
    private final PersonService personService;

    @Autowired
    public PropertyController(PropertyService propertyService, PersonService personService) {
        this.propertyService = propertyService;
        this.personService = personService;
    }

    @GetMapping("")
    public String index(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "9") int size, Model model) {
        Page<Properties> propertyPage = propertyService.findAll(page, size);

        model.addAttribute("properties", propertyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", propertyPage.getTotalPages());

        return "property/index";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("properties", new Properties());
        model.addAttribute("people", personService.findAll());
        return "property/new";
    }

    @PostMapping("")
    public String saveProperty(@ModelAttribute("properties") @Valid Properties properties,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("people", personService.findAll());
            return "property/new";
        }
        propertyService.save(properties);
        return "redirect:/properties";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model,
                           @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("properties", propertyService.findById(id));
        model.addAttribute("currentPage", page);
        return "property/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("properties", propertyService.findById(id));
        model.addAttribute("people", personService.findAll());
        return "property/edit";
    }

    @PostMapping("/{id}")
    public String save(@PathVariable("id") int id,
                       @ModelAttribute("properties") @Valid Properties properties,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("people", personService.findAll());
            return "property/edit";
        }
        propertyService.update(id, properties);
        return "redirect:/properties";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        propertyService.delete(id);
        return "redirect:/properties";
    }
}