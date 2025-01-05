package org.example.monopatinmicroservice.controllers;

import org.example.monopatinmicroservice.dtos.MonopatinConIdParadaDTO;
import org.example.monopatinmicroservice.dtos.MonopatinKmDTO;
import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.services.MonopatinService;
import org.example.monopatinmicroservice.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {
    @Autowired
    private MonopatinService monopatinService;

    @Autowired
    private ParadaService paradaService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Monopatin> result = this.monopatinService.getAll();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/id/{id_monopatin}") //Andando
    public ResponseEntity<?> getMonopatin(@PathVariable("id_monopatin") Long id) {
        try {
            Monopatin result = this.monopatinService.getById(id);

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "El monopatin con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addMonopatin(@RequestBody MonopatinConIdParadaDTO monopatinDTO) { //Agregar monopatin
        try {
            Long paradaId = monopatinDTO.getId_parada();
            Parada parada = null;

            if (paradaId != null) {
                parada = paradaService.getById(paradaId);
            }

            Monopatin monopatin = new Monopatin();

            if (parada != null) {
                if (parada.isHabilitada()) {
                    monopatin.setParada(parada);
                }
            }
            monopatin.setPosX(monopatinDTO.getPosX());
            monopatin.setPosY(monopatinDTO.getPosY());

            monopatin.setKmRecorridos(monopatinDTO.getKmRecorridos());

            Monopatin result = this.monopatinService.add(monopatin);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //ANDA
    @GetMapping("/xViajes/{xViajes}/anio/{anio}")
    public ResponseEntity<?> getMonopatinesPorViajesPorAnio(@PathVariable("anio") Integer anio, @PathVariable("xViajes") Integer xViajes) {
        try {
            List<Monopatin> result = this.monopatinService.getMonopatinesPorViajesPorAnio(anio, xViajes);

            if (!result.isEmpty()) {
                return ResponseEntity.status(200).body(result);
            } else {
                System.out.println("entré acá no?");
                return ResponseEntity.status(400).body("no existen viajes con esos parámetros");
            }
        } catch (Exception e) {
            System.out.println("acá no debo entrar");
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id_monopatin}")
    public ResponseEntity<?> deleteMonopatin(@PathVariable("id_monopatin") Long id) { //Quitar monopatin
        try {
            Monopatin result = this.monopatinService.delete(id);

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "El monopatin con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //ANDA
    @GetMapping("/location/{posx}/{posy}")
    public ResponseEntity<?> getClosestMonopatins(@PathVariable("posx") int posx, @PathVariable("posy") int posy){
        try{
            return  ResponseEntity.ok().body(this.monopatinService.getClosestMonopatins(posx, posy));
        }catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    //ANDA
    @GetMapping("/mantenimiento")
    public ResponseEntity<?> getMonopatinesEnMantenimiento(){
        try{
            ArrayList<Monopatin> monopatins = this.monopatinService.getMonopatinsByStatus("no disponible");
            return ResponseEntity.status(200).body(monopatins);
        }catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    //ANDA
    @GetMapping("/activos")
    public ResponseEntity<?> getMonopatinesActivos(){
        try{
            ArrayList<Monopatin> monopatins = this.monopatinService.getMonopatinsByStatus("activo");
            return ResponseEntity.status(200).body(monopatins);
        }catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    //ANDA
    @PostMapping("/mantener/{id_monopatin}") //Registrar monopatin en mantenimiento
    public ResponseEntity<?> enviarMonopatinAMantenimiento(@PathVariable("id_monopatin") Integer id_monopatin){
        return this.monopatinService.enviarMonopatinAMantenimiento(id_monopatin);
    }


    //ANDA
    @PutMapping("/mantenimiento/id/{id}/estado/{estado}")
    public ResponseEntity<?> cambiarEstadoMonopatin(@PathVariable("estado") String estado, @PathVariable("id") Integer id){
        return this.monopatinService.cambiarEstadoMonopatin(id, estado);
    }

    @GetMapping("/tiemposDePausa/{pausa}")
    public ResponseEntity<ArrayList<MonopatinKmDTO>> getMonopatinesPorKM(@PathVariable("pausa") boolean pausa){
        try{
            ArrayList<MonopatinKmDTO> monopatins = this.monopatinService.getMonopatinesPorKM(pausa);
            return ResponseEntity.status(200).body(monopatins);
        }catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

}
