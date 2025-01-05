
package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {
}
