package org.example.usermicroservice.repositories;

import org.example.usermicroservice.entities.Account;
import org.example.usermicroservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.role.role = :rolName")
    List<User> getUsersByRole(@Param("rolName") String rolName);

    @Query("SELECT u FROM User u WHERE u.email LIKE :email")
    ArrayList<User> getUserByEmail(@Param("email") String email);
}
