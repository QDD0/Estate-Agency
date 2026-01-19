package org.example.estateagency.controllers;

import org.example.estateagency.models.Agencies;
import org.example.estateagency.repositories.PersonRepository;
import org.example.estateagency.services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/agency")
public class AgencyController {
    private AgencyService agencyService;
    private PersonRepository personRepository;

    @Autowired
    public AgencyController(AgencyService agencyService, PersonRepository personRepository) {
        this.agencyService = agencyService;
        this.personRepository = personRepository;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Agencies> agenciesPage = agencyService.findAll(page, size);

        model.addAttribute("agencies", agenciesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", agenciesPage.getTotalPages());

        return "agency/index";
    }
}
