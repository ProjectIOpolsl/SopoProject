package com.grupa1.SopoProject.forApiDocs;

import com.grupa1.SopoProject.handlers.ValidationException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Michal on 15.01.2019
 */
@Setter
@Getter
public class WSCreateNewProject {

    @ApiModelProperty(notes = "Project name", example = "Karuzela kolorowa")
    private String projectName;

    @ApiModelProperty(notes = "Budget of project", example = "20032.34")
    private Double budget;

    @ApiModelProperty(notes = "Neighbourhood code", example = "Wojska Polskiego")
    private String neighbourhood;

    @ApiModelProperty(notes = "Description of project", example = "Projekt fajnej")
    private String description;

    @ApiModelProperty(notes = "Address", example = "Kamienna 15A")
    private String address;


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

}
