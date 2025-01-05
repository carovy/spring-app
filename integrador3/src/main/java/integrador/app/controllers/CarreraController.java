package integrador.app.controllers;

import integrador.app.dtos.ReporteCarrerasDTO;
import integrador.app.entities.Alumno;
import integrador.app.entities.Carrera;
import integrador.app.services.CarreraService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carreras")
public class CarreraController {
    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Carrera carrera) {
        try{
            Carrera a = this.carreraService.save(carrera);
            return ResponseEntity.status(HttpStatus.CREATED).body(a);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        try{
            Carrera a = this.carreraService.findById(id);

            if (a == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Carrera no encontrada");

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(a);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try{
            boolean deleted = this.carreraService.delete(id);

            if (deleted) {
                Map<String, String> response = new HashMap<>();
                response.put("msg", "Carrera con id " + id + " eliminada.");

                return ResponseEntity.status(HttpStatus.OK).body(response);

            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Carrera no encontrada");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/inscriptos")
    public ResponseEntity<?> getCarrerasConInscriptos(){
        ArrayList<Carrera> carreras = new ArrayList<>();
        carreras = this.carreraService.getCarrerasConInscriptos();
        return ResponseEntity.status(HttpStatus.OK).body(carreras);
    }

    @GetMapping("/populate")
    public ResponseEntity<?> populate() {
        Carrera c1 = new Carrera("tudai", 3);
        Carrera c2 = new Carrera("sistemas", 5);
        Carrera c3 = new Carrera("economicas", 5);
        Carrera c4 = new Carrera("bioquimica", 6);
        Carrera c5 = new Carrera("historia", 6);

        try{
            this.carreraService.save(c1);
            this.carreraService.save(c2);
            this.carreraService.save(c3);
            this.carreraService.save(c4);
            this.carreraService.save(c5);

            Map<String, String> response = new HashMap<>();
            response.put("msg", "Tabla carreras cargada");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/report")
    public ResponseEntity<?> getMajorsReport(){
        try{
            List<ReporteCarrerasDTO> result = this.carreraService.getMajorsReport();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
