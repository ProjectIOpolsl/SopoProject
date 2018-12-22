package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Michal on 05.12.2018
 */

@Entity
@Table(name = "User")
@Getter
@Setter
public class User extends AuditItem {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @OneToOne
    private Address address;

    @Column(name = "identifierNo")
    private String identifierNo;




}
