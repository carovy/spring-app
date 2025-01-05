package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.repositories.PausaRepository;
import org.example.monopatinmicroservice.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private PausaRepository pausaRepository;

    public List<Viaje> getAll() {
        return viajeRepository.findAll();
    }

    public Viaje add(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    public Viaje getById(Long id) {
        return viajeRepository.findById(id).orElse(null);
    }

    public Viaje delete(Long id) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);

        pausaRepository.deleteByViaje(viaje);

        if (viaje != null) {
            viajeRepository.deleteById(id);
        }

        return viaje;
    }

    public List<Viaje> getViajesBetween(LocalDate start, LocalDate end) {
        return viajeRepository.getTotalBilled(start, end);
    }

    public ArrayList<Viaje> getViajesByIdMonopatin(Long id) {
        ArrayList<Viaje> viajes = this.viajeRepository.getViajesByIdMonopatin(id);
        return viajes;
    }
}
