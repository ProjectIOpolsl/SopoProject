package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Michal on 22.12.2018
 */

@Entity
@Table(name = "ProjectComment")
@Getter
@Setter
public class ProjectComment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToMany(mappedBy = "projectComments")
    private List<Project> project;

    @Column(name = "Comment")
    private String comment;
}
