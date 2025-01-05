package integrador.app.services;

import integrador.app.dtos.ReporteCarrerasDTO;
import integrador.app.entities.Carrera;
import integrador.app.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("CarreraService")
public class CarreraService implements BaseService<Carrera> {
    @Autowired
    private CarreraRepository carreraRepository;
    public CarreraService() {

    }

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    public List<Carrera> findAll(Sort s) throws Exception {
        return carreraRepository.findAll(s);
    }

    @Override
    public Carrera findById(Long id) throws Exception {
        Optional<Carrera> c = carreraRepository.findById(id);
        return c.orElse(null);
    }

    public Optional<Carrera> findByName(String name){
        return Optional.ofNullable(this.carreraRepository.getMajorByName(name));
    }

    @Override
    public Carrera save(Carrera entity) throws Exception {
        return carreraRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

    public ArrayList<Carrera> getCarrerasConInscriptos() {
        return this.carreraRepository.getCarrerasConInscriptos();
    }

    public List<ReporteCarrerasDTO> getMajorsReport() {

        ArrayList<Object[]> listGraduaciones = carreraRepository.getMajorsGraduation();

        ArrayList<Object[]> listInscripciones = carreraRepository.getMajorsInscription();

        ArrayList<ReporteCarrerasDTO> dtoList = new ArrayList<>();

        HashMap<String, HashMap<Integer, ArrayList<Integer>>> union = new HashMap<>();

        for (Object[] o : listInscripciones) {
            String carrera = (String) o[0];
            Long inscriptos = (Long) o[1];
            int nInscriptos = Math.toIntExact(inscriptos);
            Long auxAnio = (Long) o[2];
            int anio = Math.toIntExact(auxAnio);

            if (!union.containsKey(carrera)) {
                union.put(carrera, new HashMap<>());
            }

            if (!union.get(carrera).containsKey(anio)) {
                union.get(carrera).put(anio, new ArrayList<>());
            }

            union.get(carrera).get(anio).add(nInscriptos);
        }

        for (Object[] o : listGraduaciones) {
            String carrera = (String) o[0];
            Long graduados = (Long) o[1];
            int nGraduados = Math.toIntExact(graduados);
            Long auxAnio = (Long) o[2];
            int anio = Math.toIntExact(auxAnio);

            HashMap<Integer, ArrayList<Integer>> aux = union.get(carrera);

            if (!aux.containsKey(anio)) {
                aux.put(anio, new ArrayList<>());
                aux.get(anio).add(0);
            }

            aux.get(anio).add(nGraduados);
        }

        List<String> carrerasKeys = new ArrayList<>(union.keySet());
        Collections.sort(carrerasKeys);

        for (String carrera : carrerasKeys) {
            List<Integer> aniosKeys = new ArrayList<>(union.get(carrera).keySet());
            Collections.sort(aniosKeys);

            for (Integer anio : aniosKeys) {
                ReporteCarrerasDTO dto = new ReporteCarrerasDTO();
                dto.setNombreCarrera(carrera);
                dto.setAnio(anio);

                ArrayList<Integer> n = union.get(carrera).get(anio);

                dto.setInscriptos(n.get(0));

                if (n.size() > 1) {
                    dto.setEgresados(n.get(1));
                } else {
                    dto.setEgresados(0);
                }

                dtoList.add(dto);
            }
        }

        return dtoList;
    }
}
