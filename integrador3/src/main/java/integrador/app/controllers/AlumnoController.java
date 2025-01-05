package integrador.app.controllers;

import integrador.app.entities.Alumno;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import integrador.app.services.AlumnoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Alumno alumno) {
        try{
            Alumno a = this.alumnoService.save(alumno);
            return ResponseEntity.status(HttpStatus.CREATED).body(a);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        try{
            Alumno a = this.alumnoService.findById(id);

            if (a == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Alumno no encontrado");

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
            boolean deleted = this.alumnoService.delete(id);

            if (deleted) {
                Map<String, String> response = new HashMap<>();
                response.put("msg", "Alumno con id " + id + " eliminado.");

                return ResponseEntity.status(HttpStatus.OK).body(response);

            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Alumno no encontrado");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/populate")
    public ResponseEntity<?> populateAlumnos() {
        Alumno a1 = new Alumno("Manuel", "Alvarez", 30, "masculino", 37766544, "tandil",23980);
        Alumno a2 = new Alumno("Milagros", "Alvarez", 19, "femenino", 46001923, "tandil", 66756);
        Alumno a3 = new Alumno("Facundo", "Bravo", 19, "masculino", 45801635, "tandil", 91218);
        Alumno a4 = new Alumno("Carolina", "Vytas Tuckus", 21, "femenino", 42768998, "capital federal", 26611);
        Alumno a5 = new Alumno("Ignacio", "Barranquero", 32, "masculino", 35666555, "tandil",998866);
        Alumno a6 = new Alumno("Azul", "Senn", 27, "femenino", 40092717, "tandil", 188765);
        Alumno a7 = new Alumno("Roman", "Mezclazcke", 19, "masculino", 47777683, "tandil", 242925);
        Alumno a8 = new Alumno("Fito", "Paez", 61, "masculino", 11948343, "capital federal", 658474);

        try {
            save(a1);
            save(a2);
            save(a3);
            save(a4);
            save(a5);
            save(a6);
            save(a7);
            save(a8);

            Map<String, String> response = new HashMap<>();
            response.put("msg", "Tabla alumnos cargada");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/genero/{genre}")
    public ResponseEntity<?> getEstudiantesByGenero(@PathVariable(name = "genre") String genero) {
            try{
            if(!genero.equals("femenino") && !genero.equals("masculino")){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Género no valido");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            ArrayList<Alumno> alumnos = this.alumnoService.getEstudiantesByGenero(genero);
            return ResponseEntity.status(HttpStatus.OK).body(alumnos);
        } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/libreta/{nro}")
    public ResponseEntity<?> getEstudianteByLibreta(@PathVariable(name = "nro") int nroLibreta) {
        try{
            Alumno a = this.alumnoService.getEstudianteByLibreta(nroLibreta);
            if(a == null){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Estudiante con nro de libreta " + nroLibreta + " no encontrado");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.status(HttpStatus.OK).body(a);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/criterio/{criterio}")
    public ResponseEntity<?> getEstudiantesByCriterio(@PathVariable("criterio") String criterio) {
        try{
            ArrayList<Alumno> alumnos = this.alumnoService.getEstudiantesByCriterio(criterio);
            return ResponseEntity.status(HttpStatus.OK).body(alumnos);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>( errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
