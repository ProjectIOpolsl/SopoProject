package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.dto.LoginResponse;
import com.grupa1.SopoProject.dto.WSError;
import com.grupa1.SopoProject.dto.WSProject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michal on 22.12.2018
 */

@Api(value = "Projects resource", description = "Endpoint for managing projects",
        consumes = "application/json", produces = "application/json",tags = {"Projects Resource"})
@RestController
@RequestMapping("/projectManagement")
public class ProjectResource {

    @ApiOperation(value = "Create project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Project created", response = LoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class)
    })
    @PostMapping(value = "/createProject", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createProject(@RequestBody WSProject wsProject){
        return null;
    }

    @ApiOperation(value = "Delete project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Project deleted", response = LoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class)
    })
    @PostMapping(value = "deleteProject", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> deleteProject(@RequestBody WSProject wsProject){
        return null;
    }
}
