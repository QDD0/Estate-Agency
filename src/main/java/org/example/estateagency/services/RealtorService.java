package org.example.estateagency.services;

import jakarta.transaction.Transactional;
import org.example.estateagency.models.Realtors;
import org.example.estateagency.repositories.RealtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RealtorService {
    private final RealtorRepository realtorRepository;

    @Autowired
    public RealtorService(RealtorRepository realtorRepository) {
        this.realtorRepository = realtorRepository;
    }

    public List<Realtors> findAll() {
        return realtorRepository.findAll();
    }

    public Page<Realtors> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return realtorRepository.findAll(pageRequest);
    }

    public Realtors findById(int id) {
        Optional<Realtors> optional = realtorRepository.findById((long) id);
        return optional.orElse(null);
    }

    @Transactional
    public void save(Realtors realtor) {
        realtorRepository.save(realtor);
    }

    @Transactional
    public void delete(int id) {
        realtorRepository.deleteById((long) id);
    }
}
