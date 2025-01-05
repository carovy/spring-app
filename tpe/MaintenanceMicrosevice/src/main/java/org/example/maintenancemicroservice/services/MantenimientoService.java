package org.example.maintenancemicroservice.services;

import jakarta.transaction.Transactional;
import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.feignClients.ReportsFeignClient;
import org.example.maintenancemicroservice.models.Monopatin;
import org.example.maintenancemicroservice.repositories.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("MantenimientoService")
public class MantenimientoService {
    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private ReportsFeignClient reportsFeignClient;

    public ArrayList<Mantenimiento> getAll(String status) {
        return this.mantenimientoRepository.findAllByStatus(status);
    }

    public Mantenimiento save(Long idMonopatin) {
        Mantenimiento mantenimiento = new Mantenimiento(idMonopatin, "no disponible");
        Mantenimiento result = this.mantenimientoRepository.save(mantenimiento);
        return result;
    }

    @Transactional
    public Mantenimiento updateMaintenance(Long id, String status) {
        Mantenimiento mantenimiento = this.mantenimientoRepository.findByIdMonopatin(id);
        if (mantenimiento != null) {
            this.mantenimientoRepository.update(id, status);
            mantenimiento.setEstado(status);
        }
        return mantenimiento;
    }

    public Mantenimiento findByMonopatinId(Long idMonopatin) {
        return this.mantenimientoRepository.findByMonopatinId(idMonopatin);
    }

    public ArrayList<Mantenimiento> getAllManteinances() {
        return (ArrayList<Mantenimiento>) this.mantenimientoRepository.findAll();
    }

    public ResponseEntity<?> getMonopatinesPorKm(boolean pausa) {
        System.out.println("hola service");
        return this.reportsFeignClient.getReporteUsoMonopatinKm(pausa);
    }
}
