package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Michal on 22.12.2018
 */
@Entity
@Table(name = "Project")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "projectName")
    private String projectName;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "budget")
    private Double budget;

    @ManyToOne
    @JoinColumn(name = "neighbourhoodId")
    private Neighbourhood neighbourhood;

    @Column(name = "description")
    private String description;

    @Column(name = "voteAmount")
    private Long voteAmount;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "projectComments",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "commentId")
    )
    private List<ProjectComment> projectComments;

    public Project() {
    }
}
