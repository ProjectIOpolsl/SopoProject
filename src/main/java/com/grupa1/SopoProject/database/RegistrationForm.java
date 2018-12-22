package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

/**
 * @author Michal on 22.12.2018
 */

@Entity
@Table(name = "RegistrationForm")
@Getter
@Setter
public class RegistrationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "neighbourhoodId")
    private Neighbourhood neighbourhood;

    @Column(name = "identifierNo")
    private String identifierNo;

    @PersistenceConstructor
    public RegistrationForm() {
    }

    public RegistrationForm(String firstName, String surname, Integer age, Neighbourhood neighbourhood, String identifierNo) {
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.neighbourhood = neighbourhood;
        this.identifierNo = identifierNo;
    }
}
