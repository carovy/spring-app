package main.java.org.example.repositories;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Alumno_Carrera;
import main.java.org.example.entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class AlumnoCarreraRepositoryImpl implements AlumnoCarreraRepository {
    private EntityManager em;

    public AlumnoCarreraRepositoryImpl(EntityManager e){
        this.em = e;
    }


    @Override
    public void matricularAlumno(Alumno a, Carrera c, Integer inscripcion, Integer graduacion, Integer antiguedad) {
        em.getTransaction().begin();

        Alumno_Carrera ac = new Alumno_Carrera(a, c, inscripcion, graduacion, antiguedad);

        em.persist(ac);
        em.getTransaction().commit();
    }

    @Override
    public Alumno_Carrera buscarAlumnoID(Alumno_Carrera id) {
        TypedQuery<Alumno_Carrera> query = em.createQuery("SELECT ac " +
                        "FROM Alumno_Carrera ac " +
                        "WHERE ac.id = :acId"
                , Alumno_Carrera.class);
        query.setParameter("acId", id);

        return query.getSingleResult();
    }

    public Alumno_Carrera buscarAlumnoID(int id) {
        TypedQuery<Alumno_Carrera> query = em.createQuery(
                "SELECT ac " +
                        "FROM Alumno_Carrera ac " +
                        "WHERE ac.alumno.id = :acId", Alumno_Carrera.class);

        query.setParameter("acId", id);
        return query.getSingleResult();
    }

}
