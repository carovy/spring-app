package org.example.maintenancemicroservice.repositories;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento,Long> {

    @Query("SELECT m FROM Mantenimiento m WHERE m.estado =:status")
    ArrayList<Mantenimiento> findAllByStatus(@PathVariable("status") String status);

    @Query("SELECT m FROM Mantenimiento m WHERE m.id_monopatin =:id")
    Mantenimiento findByIdMonopatin(@Param("id")Long id);

    @Modifying
    @Query("UPDATE Mantenimiento m SET m.estado = :status WHERE m.id_monopatin = :id")
    void update(@Param("id") Long id, @Param("status") String status);

    @Query("SELECT m FROM Mantenimiento m WHERE m.id_monopatin =:idMonopatin")
    Mantenimiento findByMonopatinId(@PathVariable("idMonopatin")Long idMonopatin);
}
