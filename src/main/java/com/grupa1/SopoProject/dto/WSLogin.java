package com.grupa1.SopoProject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 22.12.2018
 */
@Getter
@Setter
public class WSLogin implements DTO{

    @ApiModelProperty(notes = "Username", example = "abc@op.pl")
    private String username;

    @ApiModelProperty(notes = "Password", example = "passwd")
    private String password;
}
