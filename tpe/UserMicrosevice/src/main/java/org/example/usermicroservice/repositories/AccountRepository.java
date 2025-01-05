package org.example.usermicroservice.repositories;

import jakarta.transaction.Transactional;
import org.example.usermicroservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Account c SET c.anullated = :status WHERE c.id = :id")
    void setAccountAnullated(@Param("id") Long id, @Param("status") boolean status);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.balance =:monto WHERE a.id =:idCuenta")
    void setNuevoMonto(@Param("idCuenta") Long idCuenta, @Param("monto") int monto);
}
