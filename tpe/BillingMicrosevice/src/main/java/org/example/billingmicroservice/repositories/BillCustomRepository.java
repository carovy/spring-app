package org.example.billingmicroservice.repositories;

import org.example.billingmicroservice.entities.Bill;

import java.time.LocalDate;

public interface BillCustomRepository {
    void modify(String id, double price, double additionalPrice);
    void setNewPrice(String id, double price);
    Bill getLastOne();
}

