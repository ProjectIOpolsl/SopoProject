package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.dto.WSUser;
import com.grupa1.SopoProject.repositories.RegistrationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Michal on 22.12.2018
 */

@RestController
@RequestMapping("/admin")
public class AdministrationManagementResource {

    @Autowired
    private RegistrationFormRepository registrationFormRepository;

    @GetMapping(value = "/getRegistrationForms", produces = "application/json")
    public ResponseEntity<?> getRegistrationForms(){
        return null;
    }

    @PostMapping(value = "/enrollUser",consumes = "application/json")
    public ResponseEntity<?> enrollUser(@RequestBody WSUser wsUser){
        registrationFormRepository.findByIdentifierNo(wsUser.getIdentifierNo());
        return  null;

    }

}
