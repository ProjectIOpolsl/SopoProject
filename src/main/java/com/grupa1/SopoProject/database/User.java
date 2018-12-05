package com.grupa1.SopoProject.database;

import javax.persistence.*;

/**
 * @author Michal on 05.12.2018
 */

@Entity
public class User extends AuditItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;



}
