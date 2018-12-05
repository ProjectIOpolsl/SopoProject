package com.grupa1.SopoProject.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michal on 05.12.2018
 */

@RestController
@RequestMapping("/login")
public class LoginResource {

    /*
    Dummy endpoint for tests
     */
    @GetMapping
    public ResponseEntity<?> logIn(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
