package org.example.estateagency.controllers;

import org.example.estateagency.models.SalesHistory;
import org.example.estateagency.services.SalesHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/history")
public class SalesHistoryController {
    private final SalesHistoryService historyService;

    @Autowired
    public SalesHistoryController(SalesHistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size) {
        Page<SalesHistory> salesHistoryPage = historyService.findAll(page, size);

        model.addAttribute("history", salesHistoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", salesHistoryPage.getTotalPages());

        return "history/index";
    }
}
