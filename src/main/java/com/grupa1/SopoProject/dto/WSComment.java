package com.grupa1.SopoProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 09.01.2019
 */
@Getter
@Setter
public class WSComment {

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("projectId")
    private Long projectId;

    public WSComment() {
    }

    public WSComment(String comment, Long projectId) {
        this.comment = comment;
        this.projectId = projectId;
    }
}
