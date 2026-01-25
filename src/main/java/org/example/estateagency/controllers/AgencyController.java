package org.example.estateagency.controllers;

import jakarta.validation.Valid;
import org.example.estateagency.models.Agencies;
import org.example.estateagency.repositories.AgencyRepository;
import org.example.estateagency.services.AgencyService;
import org.example.estateagency.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/agency")
public class AgencyController {
    private AgencyService agencyService;
    private PersonService personService;
    private AgencyRepository agencyRepository;

    @Autowired
    public AgencyController(AgencyService agencyService, AgencyRepository agencyRepository, PersonService personService) {
        this.agencyService = agencyService;
        this.agencyRepository = agencyRepository;
        this.personService = personService;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Agencies> agenciesPage = agencyService.findAll(page, size);

        model.addAttribute("agencies", agenciesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", agenciesPage.getTotalPages());

        return "agency/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model, @RequestParam(defaultValue = "0") int page) {
        List<Agencies> byName = agencyRepository.findByName(query);

        List<Agencies> agenciesList = new ArrayList<>();
        agenciesList.addAll(byName);

        model.addAttribute("agencies", agenciesList);
        model.addAttribute("currentPage", page);
        model.addAttribute("query", query);

        return "agency/search";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("agency", new Agencies());
        model.addAttribute("people", personService.findAll());
        return "agency/new";
    }

    @PostMapping("")
    public String save(@ModelAttribute("agency") @Valid Agencies agency, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "agency/new";
        }
        agencyService.save(agency);
        model.addAttribute("people", personService.findAll());
        return "redirect:/agency";
    }

    @GetMapping("/{id}")
    public String showById(Model model, @PathVariable("id") Integer id, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("agency", agencyService.findById(id));
        model.addAttribute("currentPage", page);
        return "agency/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("agency", agencyService.findById(id));
        model.addAttribute("person", personService.findAll());
        return "agency/edit";
    }

    @PostMapping("/{id}")
    public String save(@PathVariable("id") Integer id, @ModelAttribute("agency") Agencies agency) {
        agencyService.update(id, agency);
        return "redirect:/agency";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        agencyService.delete(id);
        return "redirect:/agency";
    }
}
