package org.example.estateagency.controllers;

import jakarta.validation.Valid;
import org.example.estateagency.models.Person;
import org.example.estateagency.models.Realtors;
import org.example.estateagency.repositories.AgencyRepository;
import org.example.estateagency.repositories.RealtorRepository;
import org.example.estateagency.services.RealtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/realtor")
public class RealtorController {
    private final RealtorService realtorService;
    private final AgencyRepository agencyRepository;
    private final RealtorRepository realtorRepository;

    @Autowired
    public RealtorController(RealtorService realtorService, AgencyRepository agencyRepository, RealtorRepository realtorRepository) {
        this.realtorService = realtorService;
        this.agencyRepository = agencyRepository;
        this.realtorRepository = realtorRepository;
    }

    @GetMapping("")
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size, Model model) {
        Page<Realtors> realtors = realtorService.findAll(page, size);

        model.addAttribute("realtors", realtors.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", realtors.getTotalPages());

        return "realtor/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("realtors", realtorService.findById(id));
        model.addAttribute("currentPage", page);
        return "realtor/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("realtors", realtorService.findById(id));
        model.addAttribute("agencies", agencyRepository.findAll());
        return "realtor/edit";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model, @RequestParam(defaultValue = "0") int page) {
        List<Realtors> byFirstname = realtorRepository.findByPerson_FirstnameContaining(query);
        List<Realtors> bySurname = realtorRepository.findByPerson_SurnameContaining(query);
        List<Realtors> byLastname = realtorRepository.findByPerson_LastnameContaining(query);

        System.out.println("По имени: " + byFirstname.size());
        System.out.println("По фамилии: " + bySurname.size());
        System.out.println("По отчеству: " + byLastname.size());

        List<Realtors> allResults = new ArrayList<>();
        allResults.addAll(byFirstname);
        allResults.addAll(bySurname);
        allResults.addAll(byLastname);

        model.addAttribute("realtors", allResults);
        model.addAttribute("currentPage", page);
        model.addAttribute("query", query);

        return "realtor/search";
    }

    @PostMapping("/{id}")
    public String save(@PathVariable("id") int id,
                       @ModelAttribute("realtors") @Valid Realtors realtors,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("agencies", agencyRepository.findAll());
            return "realtor/edit";
        }

        Realtors existingRealtor = realtorService.findById(id);

        if (realtors.getPerson() != null) {
            Person existingPerson = existingRealtor.getPerson();
            Person updatedPerson = realtors.getPerson();

            existingPerson.setFirstname(updatedPerson.getFirstname());
            existingPerson.setSurname(updatedPerson.getSurname());
            existingPerson.setLastname(updatedPerson.getLastname());
            existingPerson.setBirthdate(updatedPerson.getBirthdate());
            existingPerson.setPassportseries(updatedPerson.getPassportseries());
            existingPerson.setPassportnumber(updatedPerson.getPassportnumber());
            existingPerson.setPhone(updatedPerson.getPhone());
            existingPerson.setCountry(updatedPerson.getCountry());
            existingPerson.setCity(updatedPerson.getCity());
        }

        if (realtors.getAgency() != null && realtors.getAgency().getId_agency() != null) {
            existingRealtor.setAgency(realtors.getAgency());
        }

        realtorService.save(existingRealtor);
        return "redirect:/realtor";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        realtorService.delete(id);
        return "redirect:/realtor";
    }
}
