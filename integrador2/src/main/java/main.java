package main.java;

import main.java.org.example.dtos.ReporteCarrerasDTO;
import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;
import main.java.org.example.factory.DaoFactory;
import main.java.org.example.helpers.tableDataPop;
import main.java.org.example.repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class main {
    public static void main(String[] args) {
        DaoFactory factory = DaoFactory.getInstance(1);
        AlumnoRepository alumnoRep = factory.getAlumnoRepository();
        CarreraRepository carreraRep = factory.getCarreraRepository();
        AlumnoCarreraRepository alumnoCarreraRep = factory.getAlumnoCarreraRepository();

        System.out.println("Populando tablas con algunos datos");
        tableDataPop.poblarTablaAlumnos(alumnoRep);
        tableDataPop.poblarTablaCarreras(carreraRep);
        tableDataPop.matricularEstudiantes(alumnoCarreraRep, alumnoRep, carreraRep);
        System.out.println("---------------");


        System.out.println("2) A) DAR DE ALTA UN ESTUDIANTE: Nicolas Simonelli");
        Alumno alum007 = new Alumno("Nicolas", "Simonelli", 29, "masculino", 38499001, "olavarria", 17101945);
        alumnoRep.crearAlumno(alum007);
        System.out.println(alumnoRep.getAlumnoByNroLibreta(17101945));
        System.out.println("---------------");


        System.out.println("2) B) MATRICULAR UN ESTUDIANTE EN UNA CARRERA: Nicolas Simonelli en bioquimica");
        Carrera carrera = carreraRep.buscarCarreraPorNombre("bioquimica");
        alumnoCarreraRep.matricularAlumno(alum007, carrera, 2022, 0, 1);
        System.out.println(alumnoCarreraRep.buscarAlumnoID(alum007.getAlumno_id()));
        System.out.println("---------------");


        System.out.println("2) C) LISTAR TODOS LOS ALUMNOS");
        List<Alumno> alumnos = alumnoRep.getAlumnos("apellido", true);

        for (Alumno a : alumnos) {
            System.out.println(a);
        }

        System.out.println("---------------");


        System.out.println("2) D) ALUMNOS POR LU: 12345");
        Alumno al = alumnoRep.getAlumnoByNroLibreta(23980);
        System.out.println(al);
        System.out.println("---------------");


        System.out.println("2) E) ALUMNOS POR GÉNERO: Fememenino");
        List<Alumno> alumnosGenero = alumnoRep.getAlumnosByGenero("femenino");

        for (Alumno a : alumnosGenero) {
            System.out.println(a);
        }

        System.out.println("---------------");


        System.out.println("2) F) TODAS LAS CARRERAS CON ALUMNOS INSCRIPTOS");
        List<Carrera> carrerasEstInscriptos = carreraRep.listarCarrerasConAlumnosInscriptos();

        for (Carrera c : carrerasEstInscriptos) {
            System.out.println(c);
        }

        System.out.println("---------------");


        System.out.println("2) G) TODOS LOS ESTUDIANTES DE LA CARRERA: Tudai Y CIUDAD: Tandil");
        Carrera c5 = carreraRep.buscarCarreraPorNombre("tudai");
        List<Alumno> listarAlumnosPorCarrera = alumnoRep.getAlumnosByMajorFilteredBy(c5, "tandil");

        for (Alumno a : listarAlumnosPorCarrera) {
            System.out.println(a);
        }

        System.out.println("---------------");


        System.out.println("3) REPORTE DE LAS CARRERAS POR NOMBRE Y EN ORDEN CRONOLOGICO");
        List<ReporteCarrerasDTO> majorsReport = carreraRep.getMajorsReport();

        for (ReporteCarrerasDTO m : majorsReport) {
            System.out.println(m);
        }
    }
}