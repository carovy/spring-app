package main.java.org.example.helpers;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class tableDataGenerator {
    public static List<Alumno> crearDatosAlumno() {
        Alumno a1 = new Alumno("Manuel", "Alvarez", 30, "masculino", 37766544, "tandil",23980);
        Alumno a2 = new Alumno("Milagros Irma", "Alvarez", 19, "femenino", 46001923, "cuzco", 66756);
        Alumno a3 = new Alumno("Facundo", "Bravo", 19, "masculino", 45801635, "tandil", 91218);
        Alumno a4 = new Alumno("Carolina", "Vytas Tuckus", 22, "femenino", 42768998, "capital federal", 26611);
        Alumno a5 = new Alumno("Ignacio", "Barranquero", 32, "masculino", 35666555, "tandil",998866);
        Alumno a6 = new Alumno("Azul", "Senn", 27, "femenino", 40092717, "tandil", 188765);
        Alumno a7 = new Alumno("Roman", "Mezclazcke", 19, "masculino", 47777683, "tandil", 242925);
        Alumno a8 = new Alumno("Fito", "Paez", 61, "masculino", 11948343, "capital federal", 658474);

        List<Alumno> l = new ArrayList<Alumno>();

        l.add(a1);
        l.add(a2);
        l.add(a3);
        l.add(a4);
        l.add(a5);
        l.add(a6);
        l.add(a7);
        l.add(a8);

        return l;
    }

    public static List<Carrera> crearDatosCarrera() {
        Carrera c1 = new Carrera("tudai");
        Carrera c2 = new Carrera("sistemas");
        Carrera c3 = new Carrera("economicas");
        Carrera c4 = new Carrera("bioquimica");
        Carrera c5 = new Carrera("historia");

        List<Carrera> l = new ArrayList<Carrera>();

        l.add(c1);
        l.add(c2);
        l.add(c3);
        l.add(c4);
        l.add(c5);

        return l;
    }
}
