package com.grupa1.SopoProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupa1.SopoProject.database.Project;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Michal on 09.01.2019
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WSListOfProjects implements DTO{

    @JsonProperty("listOfProjects")
    public List<WSProject> listOfProjects;

    public WSListOfProjects(List<WSProject> listOfProjects) {
        this.listOfProjects = listOfProjects;
    }

    public WSListOfProjects() {
    }
}
