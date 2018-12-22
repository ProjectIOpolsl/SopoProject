package com.grupa1.SopoProject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 22.12.2018
 */
@Getter
@Setter
public class WSRegistrationForm {

    @ApiModelProperty(notes = "First name of user")
    private String firstName;

    @ApiModelProperty(notes = "Surname of user")
    private String surname;

    @ApiModelProperty(notes = "Age of user")
    private Integer age;

    @ApiModelProperty(notes = "Neighbourhood name of user ")
    private String neighbourhood;

    @ApiModelProperty(notes = "Identifier of user (eg. PESEL / Passport id)")
    private String identifierNo;
}
