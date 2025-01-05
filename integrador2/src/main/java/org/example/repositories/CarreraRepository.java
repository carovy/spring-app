package main.java.org.example.repositories;

import main.java.org.example.dtos.ReporteCarrerasDTO;
import main.java.org.example.entities.Carrera;

import java.util.List;

public interface CarreraRepository {

     void crearCarrera(Carrera c);
     List<Carrera> listarCarreras();
     Carrera buscarCarreraPorNombre(String c);
     List<Carrera> listarCarrerasConAlumnosInscriptos();
     List<ReporteCarrerasDTO> getMajorsReport();
}
