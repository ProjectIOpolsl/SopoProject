package com.grupa1.SopoProject.forApiDocs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michal on 10.01.2019
 */

public class ApiComment {

    @ApiModelProperty(notes = "Comment on project", example = "Ale zajefajny projekt. Propsuje")
    private String comment;
    @ApiModelProperty(notes = "Project id", example = "1")
    private Long projectId;
}
