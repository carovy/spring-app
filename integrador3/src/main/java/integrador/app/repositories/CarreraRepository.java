package integrador.app.repositories;

import integrador.app.dtos.ReporteCarrerasDTO;
import integrador.app.entities.Alumno;
import integrador.app.entities.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface CarreraRepository extends RepoBase<Carrera, Long> {

    @Query("SELECT c " +
            "FROM Carrera c " +
            "JOIN c.alumnos ac " +
            "GROUP BY c " +
            "HAVING COUNT(ac.alumno) > 0 " +
            "ORDER BY COUNT(ac.alumno) DESC")
    ArrayList<Carrera> getCarrerasConInscriptos();

    @Query("SELECT c FROM Carrera c WHERE c.nombre = :nombre")
    Carrera getMajorByName(@Param("nombre") String nombre);

    @Query("SELECT c.nombre, COUNT(ac.graduacion), ac.graduacion " +
            "FROM Carrera c JOIN Alumno_Carrera ac " +
            "ON c.carrera_id = ac.id.carrera_id " +
            "WHERE ac.graduacion > 0 " +
            "GROUP BY c.nombre, ac.graduacion " +
            "ORDER BY c.nombre ASC, ac.graduacion ASC ")
    ArrayList<Object[]> getMajorsGraduation();

    @Query("SELECT c.nombre, COUNT(ac.inscripcion), ac.inscripcion " +
            "FROM Carrera c JOIN Alumno_Carrera ac " +
            "ON c.carrera_id = ac.id.carrera_id " +
            "GROUP BY c.nombre, ac.inscripcion " +
            "ORDER BY c.nombre ASC, ac.inscripcion ASC ")
    ArrayList<Object[]> getMajorsInscription();
}
