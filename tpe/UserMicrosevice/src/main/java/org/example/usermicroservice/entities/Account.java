package org.example.usermicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Date creation_date;

    @Column
    private Integer balance;

    @Column
    private boolean anullated;


    @ManyToMany(mappedBy = "accounts") // Especificamos el lado inverso de la relación
    private List<User> users;

    public Account() {
        this.creation_date = new Date();
        this.balance = 0;
        this.anullated = false;
        this.users = new ArrayList<>();
    }


}
