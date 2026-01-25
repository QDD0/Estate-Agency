package org.example.estateagency.services;

import jakarta.transaction.Transactional;
import org.example.estateagency.models.Properties;
import org.example.estateagency.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PropertyService {
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Properties> findAll() {
        return propertyRepository.findAll();
    }

    public Page<Properties> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return propertyRepository.findAll(pageRequest);
    }

    @Transactional
    public Properties save(Properties property) {
        return propertyRepository.save(property);
    }

    @Transactional
    public void update(int id, Properties updateProperty) {
        updateProperty.setId_property(id);
        propertyRepository.save(updateProperty);
    }

    public Properties findById(int id) {
        return propertyRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(int id) {
        propertyRepository.deleteById(id);
    }
}
