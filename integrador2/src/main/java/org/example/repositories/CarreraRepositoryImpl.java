package main.java.org.example.repositories;

import main.java.org.example.dtos.ReporteCarrerasDTO;
import main.java.org.example.entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.*;

public class CarreraRepositoryImpl implements CarreraRepository {
    private EntityManager em;

    public CarreraRepositoryImpl(EntityManager e){
        this.em=e;
    }

    @Override
    public void crearCarrera(Carrera c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }


    public Carrera buscarCarreraPorNombre(String c) {
        TypedQuery<Carrera> query = em.createQuery("SELECT c " +
                        "FROM Carrera c " +
                        "WHERE c.nombre = :carreraBuscada"
                , Carrera.class);
        query.setParameter("carreraBuscada", c);

        Carrera carrera = query.getSingleResult();

        return carrera;
    }

    @Override
    public List<Carrera> listarCarreras() {
        TypedQuery<Carrera> query = em.createQuery(
                "SELECT c " +
                        "FROM Carrera c", Carrera.class);

        List<Carrera> carreras = query.getResultList();

        return carreras;
    }

    public List<Carrera> listarCarrerasConAlumnosInscriptos() {
        TypedQuery<Carrera> query = em.createQuery(
                "SELECT c " +
                        "FROM Carrera c " +
                        "JOIN c.alumnos ac " +
                        "GROUP BY c " +
                        "HAVING COUNT(ac.alumno) > 0 " +
                        "ORDER BY COUNT(ac.alumno) DESC", Carrera.class);

        return query.getResultList();
    }

    public List<ReporteCarrerasDTO> getMajorsReport() {
        String jpqlGraduados = "SELECT c.nombre, COUNT(ac.graduacion), ac.graduacion " +
                                "FROM Carrera c JOIN Alumno_Carrera ac " +
                                "ON c.carrera_id = ac.id.carrera_id " +
                                "WHERE ac.graduacion > 0 " +
                                "GROUP BY c.nombre, ac.graduacion " +
                                "ORDER BY c.nombre ASC, ac.graduacion ASC ";

        Query q1 = em.createQuery(jpqlGraduados);

        List<Object[]> listGraduaciones = q1.getResultList();

        String jpqlInscripciones = "SELECT c.nombre, COUNT(ac.inscripcion), ac.inscripcion " +
                                    "FROM Carrera c JOIN Alumno_Carrera ac " +
                                    "ON c.carrera_id = ac.id.carrera_id " +
                                    "GROUP BY c.nombre, ac.inscripcion " +
                                    "ORDER BY c.nombre ASC, ac.inscripcion ASC ";

        Query q2 = em.createQuery(jpqlInscripciones);

        List<Object[]> listInscripciones = q2.getResultList();

        ArrayList<ReporteCarrerasDTO> dtoList = new ArrayList<>();

        HashMap<String, HashMap<Integer, ArrayList<Integer>>> union = new HashMap<>();

        for (Object[] o : listInscripciones) {
            String carrera = (String) o[0];
            Long inscriptos = (Long) o[1];
            int nInscriptos = inscriptos.intValue();
            Integer anio = (Integer) o[2];

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
            int nGraduados = graduados.intValue();
            Integer anio = (Integer) o[2];

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