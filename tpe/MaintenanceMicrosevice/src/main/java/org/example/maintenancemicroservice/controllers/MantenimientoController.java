package org.example.maintenancemicroservice.controllers;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.models.Monopatin;
import org.example.maintenancemicroservice.services.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {
    @Autowired
    private MantenimientoService ms;

    @GetMapping
    public ResponseEntity<?> getMantenimientos() {
        try{
            ArrayList<Mantenimiento> m = this.ms.getAllManteinances();
            return ResponseEntity.ok(m);
        }catch(Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/estado/{status}")
    public ResponseEntity<?> getAllManteinanceByStatus(@PathVariable("status") String status) {
        try{
            ArrayList<Mantenimiento> manteinances = this.ms.getAll(status);
            return ResponseEntity.ok(manteinances);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    //el estado por defecto va a ser no disponible, se supone que si recién se lo está agregando es porque hay que arreglarlo
    @PostMapping("/{id_monopatin}")
    public ResponseEntity<?> saveManteinance(@PathVariable("id_monopatin") Long id_monopatin){
        try{
            Mantenimiento m = this.ms.save(id_monopatin);
            return ResponseEntity.status(201).body(m);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("id/{id_monopatin}/estado/{status}") //Registrar fin de mantenimiento
    public ResponseEntity<?> updateStatus(@PathVariable("id_monopatin") int id, @PathVariable("status") String status){
        try{
            Integer idInteger = id;
            Long longId = idInteger.longValue();
            Mantenimiento mantenimiento = this.ms.updateMaintenance(longId, status);
            return ResponseEntity.ok(mantenimiento);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_monopatin}")
    public ResponseEntity<?> getManteinanceByMonopatinId(@PathVariable("id_monopatin") int id_monopatin){
        try{
            Integer idInteger = id_monopatin;
            Long longId = idInteger.longValue();
            Mantenimiento mantenimiento = this.ms.findByMonopatinId(longId);
            if(mantenimiento != null) {
                return ResponseEntity.status(200).body(mantenimiento);
            }else {
                return null;
            }
        }catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/monopatinesKm/pausa/{pausa}")
    public ResponseEntity<?> getMonopatinesPorKm(@PathVariable("pausa") boolean pausa) {
        try {
            System.out.println("hola mant controller");
            return this.ms.getMonopatinesPorKm(pausa);
        } catch (Exception e) {
            System.out.println("fuck mant controller");
            return ResponseEntity.status(500).build();
        }
    }
}
