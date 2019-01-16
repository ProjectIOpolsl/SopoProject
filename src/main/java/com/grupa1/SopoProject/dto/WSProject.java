package com.grupa1.SopoProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupa1.SopoProject.handlers.ValidationException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Michal on 22.12.2018
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class WSProject implements DTO{


    @JsonProperty("id")
    @ApiModelProperty(notes = "Project id", example = "1")
    private Long id;

    @JsonProperty("projectName")
    @ApiModelProperty(notes = "Project name", example = "Karuzela kolorowa")
    private String projectName;

    @JsonProperty("budget")
    @ApiModelProperty(notes = "Budget of project", example = "20032.34")
    private Double budget;

    @JsonProperty("neighbourhood")
    @ApiModelProperty(notes = "Neighbourhood code", example = "Wojska Polskiego")
    private String neighbourhood;

    @JsonProperty("description")
    @ApiModelProperty(notes = "Description of project", example = "Projekt fajnej")
    private String description;

    @JsonProperty("address")
    @ApiModelProperty(notes = "Address", example = "Kamienna 15A")
    private String address;

    @JsonProperty("voteAmount")
    @ApiModelProperty(notes = "VoteAmount", example = "124")
    private Long votesAmount;

    public WSProject(Long id, String projectName, Double budget, String neighbourhood, String description, String address, Long votesAmount) {
        this.id = id;
        this.projectName = projectName;
        this.budget = budget;
        this.neighbourhood = neighbourhood;
        this.description = description;
        this.address = address;
        this.votesAmount = votesAmount;
    }

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
