package com.grupa1.SopoProject.database;

import com.grupa1.SopoProject.handlers.UserAlreadyVotedException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 22.12.2018
 */
@Entity
@Table(name = "Project")
@Getter
@Setter
public class Project extends AuditItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "projectName")
    private String projectName;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "budget")
    private Double budget;

    @ManyToOne
    @JoinColumn(name = "neighbourhoodId")
    private Neighbourhood neighbourhood;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "address")
    private String address;

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
    private List<ProjectComment> projectComments = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "projectVotingUser",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> projectVotingUser = new ArrayList<>();

    public void addCommentToProject(ProjectComment projectComment){
        this.projectComments.add(projectComment);
    }

    public Project() {
    }

    public Project(String projectName, User user, Double budget, Neighbourhood neighbourhood, String description, List<ProjectComment> projectComments, String address) {
        this.projectName = projectName;
        this.user = user;
        this.budget = budget;
        this.neighbourhood = neighbourhood;
        this.description = description;
        this.voteAmount = 0L;
        this.projectComments = projectComments;
        this.address = address;
    }

    public void vote(User user) throws UserAlreadyVotedException {
        if(projectVotingUser == null){
            this.projectVotingUser = new ArrayList<>();
        }
        if(projectVotingUser.contains(user)){
            throw new UserAlreadyVotedException("User already voted for project: " + projectName);
        }
        voteAmount++;
        projectVotingUser.add(user);
    }
}
