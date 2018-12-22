package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.dto.WSRegistrationForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * @author Michal on 22.12.2018
 */
@RestController
@RequestMapping("/registration")
@Api(value="Registration Resource", description="Operations which user can made to send a " +
        "registration request")
public class RegistrationResource {

    @ApiOperation(value = "Send registration request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request processed successfully"),
            @ApiResponse(code = 400, message = "Invalid data send"),
            @ApiResponse(code = 500, message = "Internal server error. Something went wrong")
    })
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity register(@RequestBody WSRegistrationForm registrationForm){
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
