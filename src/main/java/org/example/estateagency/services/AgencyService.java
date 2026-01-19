package org.example.estateagency.services;

import jakarta.transaction.Transactional;
import org.example.estateagency.models.Agencies;
import org.example.estateagency.repositories.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgencyService {
    private final AgencyRepository agencyRepository;

    @Autowired
    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public Page<Agencies> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return agencyRepository.findAll(pageRequest);
    }

    public Agencies findById(Integer id) {
        Optional<Agencies> agency = agencyRepository.findById(id);
        return agency.orElse(null);
    }

    @Transactional
    public void save(Agencies agency) {
        agencyRepository.save(agency);
    }

    @Transactional
    public void update(Integer id, Agencies updateAgency) {
        updateAgency.setId_agency(id);
        agencyRepository.save(updateAgency);
    }

    @Transactional
    public void delete(Integer id) {
        agencyRepository.deleteById(id);
    }
}
