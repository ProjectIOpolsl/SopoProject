package com.grupa1.SopoProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupa1.SopoProject.database.Project;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Michal on 22.12.2018
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WSProject {
    @JsonProperty("projectName")
    private String projectName;

    @JsonProperty("budget")
    private Double budget;

    @JsonProperty("neighbourhood")
    private String neighbourhood;

    @JsonProperty("description")
    private String description;

    public WSProject(String projectName, Double budget, String neighbourhood, String description) {
        this.projectName = projectName;
        this.budget = budget;
        this.neighbourhood = neighbourhood;
        this.description = description;
    }

    public boolean validateData() throws ValidationException {
        if(StringUtils.isBlank(projectName)){
            throw new ValidationException("Project name");
        } else if(budget == null){
            throw new ValidationException("Budget");
        } else if(StringUtils.isBlank(neighbourhood)){
            throw new ValidationException("Neighbourhood");
        } else if(StringUtils.isBlank(description)){
            throw new ValidationException("Description");
        }
        return  true;
    }

    public WSProject() {
    }

    public String getProjectName() {
        return projectName;
    }

    public Double getBudget() {
        return budget;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getDescription() {
        return description;
    }
}
