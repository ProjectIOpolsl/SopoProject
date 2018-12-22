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
public class LoginResponse {

    @ApiModelProperty(notes = "Token property", example = "werrfdasfzcvre231223rfcs12332edsf")
    private String token;

    @ApiModelProperty(notes = "Error message", example = "123213eadszfcdfq32edfsdf")
    private String refreshToken;

    @ApiModelProperty(notes = "Error message")
    private Date timestamp;

    public LoginResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.timestamp = new Date();
    }
}
