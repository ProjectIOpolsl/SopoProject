package com.grupa1.SopoProject;

import com.grupa1.SopoProject.database.Neighbourhood;
import com.grupa1.SopoProject.database.User;
import com.grupa1.SopoProject.dto.WSRegistrationForm;
import com.grupa1.SopoProject.repositories.NeighbourhoodRepository;
import com.grupa1.SopoProject.repositories.RegistrationFormRepository;
import com.grupa1.SopoProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Michal on 22.12.2018
 */
@Service
public class RegistrationFormService {

    @Autowired
    private RegistrationFormRepository registrationFormRepository;

    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean validateRegistrationForm(WSRegistrationForm wsRegistrationForm){
        if(wsRegistrationForm.getAddress() == null){
            return false;
        } else if(wsRegistrationForm.getEmail() == null) {
            return false;
        } else if (wsRegistrationForm.getNeighbourhood() != null){
            Neighbourhood neighbourhood = neighbourhoodRepository.findByName(wsRegistrationForm.getNeighbourhood());
            return neighbourhood != null ? true : false;
        }
        if(userRepository.findUserByIdentifierNo(wsRegistrationForm.getIdentifierNo()) != null){
            return false;
        }
        return true;
    }

    public RegistrationFormRepository getRegistrationFormRepository() {
        return registrationFormRepository;
    }
}
