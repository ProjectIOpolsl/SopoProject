package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.handlers.AccountAleadyExistsException;
import com.grupa1.SopoProject.handlers.RegistrationFormAlreadyExistsException;
import com.grupa1.SopoProject.service.RegistrationFormService;
import com.grupa1.SopoProject.database.Neighbourhood;
import com.grupa1.SopoProject.database.RegistrationForm;
import com.grupa1.SopoProject.dto.WSError;
import com.grupa1.SopoProject.dto.WSRegistrationForm;
import com.grupa1.SopoProject.repositories.NeighbourhoodRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michal on 22.12.2018
 */
@RestController
@RequestMapping("/registration")
@Api(value="Registration Resource", description="Operations which user can made to send a " +
        "registration request", consumes = "application/json",
        tags = "Registration Resource")
public class RegistrationResource {

    @Autowired
    private RegistrationFormService registrationFormService;

    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;

    @ApiOperation(value = "Send registration request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request processed successfully"),
            @ApiResponse(code = 400, message = "Invalid data send"),
            @ApiResponse(code = 500, message = "Internal server error. Something went wrong")
    })
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody WSRegistrationForm registrationForm){
        boolean isValid=true;
        try {
            isValid = registrationFormService.validateRegistrationForm(registrationForm);
        } catch (RegistrationFormAlreadyExistsException e) {
            return new ResponseEntity<>(new WSError(e.getMessage(),"/registration/register"), HttpStatus.BAD_REQUEST);
        } catch (AccountAleadyExistsException e) {
            return new ResponseEntity<>(new WSError(e.getMessage(),"/registration/register"), HttpStatus.BAD_REQUEST);
        }
        if(!isValid){
            return new ResponseEntity<>(new WSError("Incorrect data passed","/registration/register"), HttpStatus.BAD_REQUEST);
        }
        //TODO hardcoded
        Neighbourhood neighbourhood = neighbourhoodRepository.findByName("Wojska Polskiego");
        RegistrationForm objectToPersist = new RegistrationForm(registrationForm.getFirstName(),
                registrationForm.getSurname(),registrationForm.getAge(),neighbourhood,
                registrationForm.getIdentifierNo(),registrationForm.getEmail(),registrationForm.getPassword());
        registrationFormService.getRegistrationFormRepository().save(objectToPersist);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Accept registration form send by citizen")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Declined registration form succeed"),
            @ApiResponse(code = 401, message = "Denied access to this endpoint"),
            @ApiResponse(code = 400, message = "Invalid request data")
    })
    @PostMapping("/declineRegistrationForm")
    @PreAuthorize(value = "ADMIN")
    public ResponseEntity<?> declineRegistrationForm(@RequestBody WSRegistrationForm registrationForm){
        return null;
    }

    @ApiOperation(value = "Accept registration form send by citizen")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Acceptance registration form succeed"),
            @ApiResponse(code = 401, message = "Denied access to this endpoint"),
            @ApiResponse(code = 400, message = "Invalid request data")
    })
    @PostMapping("/acceptRegistrationForm")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> acceptRegistrationForm(@RequestBody WSRegistrationForm registrationForm){
        return null;
    }





}
