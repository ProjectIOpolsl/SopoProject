package com.grupa1.SopoProject.forApiDocs;

import com.grupa1.SopoProject.handlers.InvalidProjectExcpetion;
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


    public boolean validateData() throws InvalidProjectExcpetion {
        if(StringUtils.isBlank(projectName) || projectName.length() >= 255){
            throw new InvalidProjectExcpetion("Project does not contain 'Project name' field or this property is badly formatted");
        } else if(StringUtils.isBlank(neighbourhood)){
            throw new InvalidProjectExcpetion("Project does not specify neighbourhood in which project is planned");
        } else if(StringUtils.isBlank(description) || description.length() > 2000){
            throw new InvalidProjectExcpetion("Project doesn't have description or have too big. Constraint is set to 2000") ;
        } else if(StringUtils.isBlank(address) || address.length() >=255){
            throw new InvalidProjectExcpetion("Project does not contain 'Address' field or this property is badly formatted") ;
        }
        return  true;
    }

}
