package com.grupa1.SopoProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 09.01.2019
 */
@Getter
@Setter
public class WSComment implements DTO{

    @JsonProperty("comment")
    @ApiModelProperty(notes = "Comment on project", example = "Ale zajefajny projekt. Propsuje")
    private String comment;

    @JsonProperty("projectId")
    @ApiModelProperty(notes = "Project id", example = "1")
    private Long projectId;

    @JsonProperty("email")
    @ApiModelProperty(hidden = true)
    private String email;

    public WSComment() {
    }

    public WSComment(String comment, Long projectId, String email) {
        this.comment = comment;
        this.projectId = projectId;
        this.email = email;
    }
}
