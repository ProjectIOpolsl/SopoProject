package com.grupa1.SopoProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 10.01.2019
 */
@Getter
@Setter
public class WSCommentResponse {

    @ApiModelProperty(notes = "Comment on project", example = "Ale zajefajny projekt. Propsuje")
    private String comment;
    @ApiModelProperty(notes = "Project id", example = "1")
    private Long projectId;
    @ApiModelProperty(notes = "email", example = "abc@op.pl")
    private String email;

    public WSCommentResponse(String comment, Long projectId, String email) {
        this.comment = comment;
        this.projectId = projectId;
        this.email = email;
    }
}
