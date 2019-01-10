package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.dto.WSError;
import com.grupa1.SopoProject.dto.WSLoginResponse;
import com.grupa1.SopoProject.dto.WSUser;
import com.grupa1.SopoProject.repositories.RegistrationFormRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Michal on 22.12.2018
 */

@RestController
@RequestMapping("/admin")
@Api(value = "Administration Management", description = "Endpoints for managing actions connected with administation",
        consumes = "application/json", produces = "application/json",tags = {"Administration Management"})
public class AdministrationManagementResource {

    @Autowired
    private RegistrationFormRepository registrationFormRepository;

    @ApiOperation(value = "Log into SOPO project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned correct values", response = WSLoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden acces. Your account has been blocked", response = WSError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = WSError.class)
    })
    @GetMapping(value = "/getRegistrationForms", produces = "application/json")
    public ResponseEntity<?> getRegistrationForms(){
        return null;
    }


    @ApiOperation(value = "Log into SOPO project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Enrolled customer correctly", response = WSLoginResponse.class),
            @ApiResponse(code = 400, message = "Incorrect data passed to server", response = WSLoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden acces. Your account has been blocked", response = WSError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = WSError.class)
    })
    @PostMapping(value = "/enrollUser",consumes = "application/json")
    public ResponseEntity<?> enrollUser(@RequestBody WSUser wsUser){
        registrationFormRepository.findByIdentifierNo(wsUser.getIdentifierNo());
        return  null;

    }

}
