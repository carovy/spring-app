package org.example.monopatinmicroservice.controllers;

import org.example.monopatinmicroservice.dtos.PausaConIdViajeDTO;
import org.example.monopatinmicroservice.entities.Pausa;
import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.services.PausaService;
import org.example.monopatinmicroservice.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/pausa")
public class PausaController {
    @Autowired
    private PausaService pausaService;

    @Autowired
    private ViajeService viajeService;

    @GetMapping //Andando
    public ResponseEntity<?> getPausas() {
        try {
            List<Pausa> result = this.pausaService.getAll();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id_pausa}") //Andando
    public ResponseEntity<?> getPausa(@PathVariable("id_pausa") Long id) {
        try {
            Pausa result = this.pausaService.getById(id);

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "La pausa con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addPausa(@RequestBody PausaConIdViajeDTO pausaDTO) {
        try {
            Long viajeId = pausaDTO.getId_viaje().longValue();

            Viaje viaje = viajeService.getById(viajeId);

            Pausa pausa = new Pausa();
            pausa.setViaje(viaje);
            pausa.setTiempo(pausaDTO.getTiempo());

            Pausa result = this.pausaService.add(pausa);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id_pausa}")
    public ResponseEntity<?> deletePausa(@PathVariable("id_pausa") Integer id) {
        try {
            Pausa result = this.pausaService.delete(id.longValue());

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "La pausa con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
