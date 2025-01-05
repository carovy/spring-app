package main.java.org.example.helpers;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;
import main.java.org.example.repositories.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tableDataPop {

    public static void poblarTablaAlumnos(AlumnoRepository alRep) {
        List<Alumno> l = new ArrayList<Alumno>(tableDataGenerator.crearDatosAlumno());

        for (Alumno alumno : l) {
            alRep.crearAlumno(alumno);
        }
    }

    public static void poblarTablaCarreras(CarreraRepository carRep) {
        List<Carrera> l = new ArrayList<Carrera>(tableDataGenerator.crearDatosCarrera());

        for (Carrera carrera : l) {
            carRep.crearCarrera(carrera);
        }
    }

    private static Carrera getRandomMajor(int n, List<Carrera> carreras){
        Random r = new Random();
        return carreras.get((r.nextInt(n)));
    }

    public static int getRandomYear(boolean puedeCero) {
        Integer[] years;

        if (puedeCero) {
            years = new Integer[]{0, 2025, 2026, 2027};
        } else {
            years = new Integer[]{2021, 2022, 2023, 2024};
        }

        int numero = (int) (Math.random() * years.length);

        return years[numero];
    }

    public static void matricularEstudiantes(AlumnoCarreraRepository acRep, AlumnoRepository alRep, CarreraRepository carRep) {
            for (Alumno al : alRep.getAlumnos("nombre", false)){
                acRep.matricularAlumno(al, getRandomMajor(5, carRep.listarCarreras()), getRandomYear(false), getRandomYear(true), 3);
            }
    }
}
