package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 05.12.2018
 */
@Entity
@Table(name = "User")
@Getter
@Setter
public class User extends AuditItem {

    @Id
    @Column(name = "userId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "secondName", nullable = false)
    private String secondName;

    @OneToOne(optional = false)
    private Address address;

    @Column(name = "identifierNo", nullable = false, unique = true)
    private String identifierNo;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "projectComments",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<ProjectComment> projectComments = new ArrayList<>();

    @ManyToMany(mappedBy = "projectVotingUser")
    private List<Project> project = new ArrayList<>();


    public User(String firstName, String secondName, Address address, String identifierNo) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.identifierNo = identifierNo;
    }

    @PersistenceConstructor
    public User() {
    }
}
