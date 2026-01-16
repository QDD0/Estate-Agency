package org.example.estateagency.controllers;

import jakarta.validation.Valid;
import org.example.estateagency.models.Person;
import org.example.estateagency.repositories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.example.estateagency.services.PersonService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonService personService, PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }

    @GetMapping("")
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                       Model model) {

        Page<Person> personPage = personService.findAll(page, size);

        model.addAttribute("people", personPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", personPage.getTotalPages());

        return "people/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model, @RequestParam(defaultValue = "0") int page) {
        List<Person> byFirstname = personRepository.findByFirstnameContainingIgnoreCase(query);
        List<Person> bySurname = personRepository.findBySurnameContainingIgnoreCase(query);
        List<Person> byLastname = personRepository.findByLastnameContainingIgnoreCase(query);

        System.out.println("По имени: " + byFirstname.size());
        System.out.println("По фамилии: " + bySurname.size());
        System.out.println("По отчеству: " + byLastname.size());

        List<Person> allResults = new ArrayList<>();
        allResults.addAll(byFirstname);
        allResults.addAll(bySurname);
        allResults.addAll(byLastname);

        model.addAttribute("people", allResults);
        model.addAttribute("currentPage", page);
        model.addAttribute("query", query);

        return "people/search";
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
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
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
