package com.grupa1.SopoProject.database;


import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

/**
 * @author Michal on 22.12.2018
 */
@Entity
public class Privilige {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "priviligeId")
    private Long priviligeId;

    @Column(name = "authority")
    private String authority;

    public Privilige(String authority) {
        this.authority = authority;
    }

    @PersistenceConstructor
    public Privilige() {}
}
