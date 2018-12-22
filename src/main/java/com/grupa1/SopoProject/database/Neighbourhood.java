package com.grupa1.SopoProject.database;

import javax.persistence.*;

/**
 * @author Michal on 22.12.2018
 */
@Entity
@Table(name = "Neighbourhood")
public class Neighbourhood extends AuditItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "neighbourhoodId")
    private Long neighbourhoodId;

    @Column(name = "Name")
    private String neighbourhoodName;
}
