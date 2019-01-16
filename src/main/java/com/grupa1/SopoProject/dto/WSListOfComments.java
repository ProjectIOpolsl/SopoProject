package com.grupa1.SopoProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupa1.SopoProject.database.ProjectComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 10.01.2019
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WSListOfComments {

    @JsonProperty("listOfComments")
    @ApiModelProperty(notes = "list of comments")
    public List<WSCommentResponse> listOfComments;

    public WSListOfComments(List<WSCommentResponse> listOfComments) {
        this.listOfComments = listOfComments;
    }

    public WSListOfComments() {
        this.listOfComments = new ArrayList<>();
    }

    public void addToList(WSCommentResponse projectComment){
        this.listOfComments.add(projectComment);
    }
}
