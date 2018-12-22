package com.grupa1.SopoProject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Michal on 22.12.2018
 */

@Getter
@Setter
public class WSError {

    @ApiModelProperty(notes = "Current time")
    private Date timestamp;

    @ApiModelProperty(notes = "Error message", example = "Invalid access token")
    private String message;

    @ApiModelProperty(notes = "Url path which caused error", example = "/registration/register")
    private String path;

    public WSError(String message, String path) {
        this.message = message;
        this.path = path;
    }
}
