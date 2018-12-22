package com.grupa1.SopoProject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 22.12.2018
 */
@Getter
@Setter
public class WSLogin {

    @ApiModelProperty(notes = "Username", example = "Tomasz")
    private String username;

    @ApiModelProperty(notes = "Password", example = "lubieRobicSprawka")
    private String password;
}
