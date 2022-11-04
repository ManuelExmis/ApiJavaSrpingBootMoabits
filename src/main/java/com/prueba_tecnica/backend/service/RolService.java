package com.prueba_tecnica.backend.service;


import com.prueba_tecnica.backend.model.Rol;
import com.prueba_tecnica.backend.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> getRolls() {
        return rolRepository.findAll();
    }
    public Optional<Rol> getById(Long id) {
        return rolRepository.findById(id);
    }
}
