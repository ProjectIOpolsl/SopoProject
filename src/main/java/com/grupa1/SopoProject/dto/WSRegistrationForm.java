package com.grupa1.SopoProject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 22.12.2018
 */
@Getter
@Setter
public class WSRegistrationForm implements DTO{

    @ApiModelProperty(notes = "First name of user", example = "Tomasz")
    private String firstName;

    @ApiModelProperty(notes = "Surname of user", example = "Wachowski")
    private String surname;

    @ApiModelProperty(hidden = true, notes = "Age of user", example = "69")
    private Integer age;

    @ApiModelProperty(hidden = true, notes = "Neighbourhood name of user", example = "Politechnika")
    private String neighbourhood;

    @ApiModelProperty(notes = "Identifier of user (eg. PESEL / Passport id)", example = "6969")
    private String identifierNo;

    @ApiModelProperty(notes = "Password", example = "tajneHaslo")
    private String password;

    @ApiModelProperty(notes = "Email", example = "email")
    private String email;

    @ApiModelProperty(notes = "Address", example = "Stawowa 21")
    private String address;


}
