package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.repositories.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService {
    @Autowired
    private ParadaRepository paradaRepository;

    public List<Parada> getAll() {
        return paradaRepository.findAll();
    }

    public Parada add(Parada parada) {
        return paradaRepository.save(parada);
    }

    public Parada getById(Long id) {
        return paradaRepository.findById(id).orElse(null);
    }

    public Parada delete(Long id) {
        Parada parada = paradaRepository.findById(id).orElse(null);

        if (parada != null) {
            paradaRepository.deleteById(id);
        }

        return parada;
    }
}
