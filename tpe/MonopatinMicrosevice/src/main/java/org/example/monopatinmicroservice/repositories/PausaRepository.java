
package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Pausa;
import org.example.monopatinmicroservice.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface PausaRepository extends JpaRepository<Pausa, Long> {

    @Query("SELECT p FROM Pausa p WHERE p.viaje.id =:id")
    ArrayList<Pausa> getPausasByViajeId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pausa p WHERE p.viaje = :viaje")
    void deleteByViaje(@Param("viaje") Viaje viaje);
}
