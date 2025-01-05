package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT m FROM Monopatin m JOIN Viaje v ON v.monopatin.id = m.id  WHERE YEAR(v.fecha) = :anio GROUP BY m HAVING COUNT(m) >= :xViajes")
    List<Monopatin> getMonopatinesPorViajesPorAnio(@Param("anio") Integer anio, @Param("xViajes") Integer xViajes);

    @Query("SELECT m FROM Monopatin  m where m.posX between  :x-500 and :x+500 AND m.posY between  :y-500 AND :y+500")
    List<Monopatin> getMonopatinesEnRadio1km(@PathVariable("x") Integer x, @PathVariable("y")Integer y);

    @Query("SELECT m.id, m.kmRecorridos, m.tiempoUso, m.tiempoUsoConPausas FROM Monopatin m where m.kmRecorridos < :maxKm")
    List<Monopatin> getAllByMaxKm(@PathVariable("maxKm") float maxKm);

    @Query("SELECT m.id, m.kmRecorridos, m.tiempoUso, m.tiempoUsoConPausas FROM Monopatin m where m.tiempoUso < :maxTiempo")
    List<Monopatin> getAllByMaxTime(@PathVariable("maxTiempo") float maxT);

    @Query("SELECT m.id, m.kmRecorridos, m.tiempoUso, m.tiempoUsoConPausas FROM Monopatin m where m.tiempoUsoConPausas < :maxTiempo")
    List<Monopatin> getAllByMaxTimeWithPauses(@PathVariable("maxTiempo") float maxT);

    @Query("SELECT m.id, m.kmRecorridos, m.tiempoUso, m.tiempoUsoConPausas FROM Monopatin m where m.kmRecorridos < :maxKm AND m.tiempoUso < :maxTiempo")
    List<Monopatin> getAllByMaxTimeAndKm(@PathVariable("maxTiempo") float maxT,@PathVariable("maxKm") float maxKm);

    @Query("SELECT m.id, m.kmRecorridos, m.tiempoUso, m.tiempoUsoConPausas FROM Monopatin m where m.kmRecorridos < :maxKm AND m.tiempoUsoConPausas < :maxTiempo")
    List<Monopatin> getAllByMaxTimeWithPausesAndKm(@PathVariable("maxTiempo") float maxT,@PathVariable("maxKm") float maxKm);

    @Query("SELECT m FROM Monopatin m ORDER BY m.kmRecorridos ASC")
    ArrayList<Monopatin> getMonopatinesPorKM();
}
