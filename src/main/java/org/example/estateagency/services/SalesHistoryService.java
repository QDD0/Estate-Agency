package org.example.estateagency.services;

import org.example.estateagency.models.SalesHistory;
import org.example.estateagency.repositories.SalesHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesHistoryService {
    private final SalesHistoryRepository historyRepository;

    @Autowired
    public SalesHistoryService(SalesHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Page<SalesHistory> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return historyRepository.findAll(pageRequest);
    }
}
