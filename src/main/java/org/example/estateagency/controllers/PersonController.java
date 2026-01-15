package org.example.estateagency.controllers;

import org.example.estateagency.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.estateagency.services.PersonService;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public String test(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                       Model model) {

        Page<Person> personPage = personService.findAll(page, size);

        model.addAttribute("people", personPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", personPage.getTotalPages());

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@RequestParam(defaultValue = "0") int page, @PathVariable int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("currentPage", page);
        return "people/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping("")
    public String save(@ModelAttribute("person") Person person) {
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personService.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
