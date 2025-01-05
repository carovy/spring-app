package main.java.org.example.repositories;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Alumno_Carrera;
import main.java.org.example.entities.Carrera;

import java.sql.Date;

public interface AlumnoCarreraRepository {
    public void matricularAlumno(Alumno a, Carrera c, Integer inscripcion, Integer graduacion, Integer antiguedad);
    public Alumno_Carrera buscarAlumnoID(Alumno_Carrera id);
    public Alumno_Carrera buscarAlumnoID(int id);
}
