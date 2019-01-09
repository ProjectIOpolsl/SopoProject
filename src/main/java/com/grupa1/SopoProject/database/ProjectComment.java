package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<Project> project = new ArrayList<>();

    @Column(name = "Comment")
    private String comment;

    public void addCommentToProject(Project project){
        if(this.project == null){
            this.project = new ArrayList<>();
        }
        this.project.add(project);
    }

    public ProjectComment() {
    }

    public ProjectComment(String comment){
        this.comment = comment;

    }
}
