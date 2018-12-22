package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Michal on 22.12.2018
 */

@Entity
@Table(name = "Address")
@Getter
@Setter
public class Address extends AuditItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "HouseNumber")
    private String houseNumber;

    @ManyToOne
    @JoinColumn(name = "neighbourhoodId")
    private Neighbourhood neighbourhood;
}
