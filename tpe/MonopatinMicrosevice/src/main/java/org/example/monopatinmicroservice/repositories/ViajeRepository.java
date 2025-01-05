package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v WHERE v.fecha >=:origin AND v.fecha <=:end")
    public List<Viaje> getTotalBilled(@PathVariable("origin") LocalDate origin, @PathVariable("end") LocalDate end);

    @Query("SELECT v FROM Viaje v WHERE v.monopatin.id =:id")
    ArrayList<Viaje> getViajesByIdMonopatin(@Param("id") Long id);
}
