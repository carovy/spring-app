package org.example.billingmicroservice.repositories;

import org.example.billingmicroservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

@Repository
public interface BillRepository extends MongoRepository<Bill,String>, BillCustomRepository  {
}