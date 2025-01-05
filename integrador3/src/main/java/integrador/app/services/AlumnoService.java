package integrador.app.services;

import integrador.app.entities.Alumno;
import integrador.app.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("AlumnoService")
public class AlumnoService implements BaseService<Alumno> {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Alumno> findAll() throws Exception {
        return this.alumnoRepository.findAll();
    }

    public List<Alumno> findAll(Sort s) throws Exception {
        return this.alumnoRepository.findAll(s);
    }

    @Override
    public Alumno findById(Long id) throws Exception {
        Optional<Alumno> a = alumnoRepository.findById(id);
        return a.orElse(null);
    }

    @Override
    public Alumno save(Alumno entity) throws Exception {
        return this.alumnoRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            alumnoRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Alumno> getEstudiantesByGenero(String genero) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos = alumnoRepository.getEstudiantesByGenero(genero);
        return alumnos;
    }

    public Alumno getEstudianteByLibreta(int nroLibreta) {
        Alumno a = this.alumnoRepository.getEstudianteByLibreta(nroLibreta);
        return a;
    }

    public ArrayList<Alumno> getEstudiantesByCriterio(String criterio) {
        List<String> posiblesCriterios = new ArrayList<>();
        posiblesCriterios.add("nombre");
        posiblesCriterios.add("dni");
        posiblesCriterios.add("edad");
        posiblesCriterios.add("apellido");

        if(!posiblesCriterios.contains(criterio)) {
            throw new IllegalArgumentException("criterio invalido: " + criterio);
        }

        Sort sort = Sort.by(Sort.Direction.ASC, criterio);
        return (ArrayList<Alumno>) alumnoRepository.findAll(sort);
    }
}
