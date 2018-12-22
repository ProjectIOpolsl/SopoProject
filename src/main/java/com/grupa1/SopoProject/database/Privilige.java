package com.grupa1.SopoProject.database;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * @author Michal on 22.12.2018
 */
@Entity
public class Privilige {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
}
