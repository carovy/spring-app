package integrador.app.repositories;

import integrador.app.entities.Alumno;
import integrador.app.entities.Alumno_Carrera;
import integrador.app.entities.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("AlumnoCarreraRepository")
public interface AlumnoCarreraRepository extends RepoBase<Alumno_Carrera, Long> {

    @Query("SELECT ac.alumno FROM Alumno_Carrera ac WHERE ac.carrera.carrera_id = :carrera AND ac.alumno.ciudad LIKE :ciudad")
    ArrayList<Alumno> getAlumnosByMajor(@Param("ciudad") String ciudad, @Param("carrera") Long carrera);
}
