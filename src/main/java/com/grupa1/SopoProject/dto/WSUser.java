package com.grupa1.SopoProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 09.01.2019
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WSUser implements DTO{

    @JsonProperty("identifierNo")
    private String identifierNo;

    public WSUser(String identifierNo) {
        this.identifierNo = identifierNo;
    }
}
