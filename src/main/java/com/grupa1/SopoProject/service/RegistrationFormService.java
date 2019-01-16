package com.grupa1.SopoProject.service;

import com.grupa1.SopoProject.database.Account;
import com.grupa1.SopoProject.database.Neighbourhood;
import com.grupa1.SopoProject.database.RegistrationForm;
import com.grupa1.SopoProject.database.User;
import com.grupa1.SopoProject.dto.WSRegistrationForm;
import com.grupa1.SopoProject.handlers.AccountAleadyExistsException;
import com.grupa1.SopoProject.handlers.RegistrationFormAlreadyExistsException;
import com.grupa1.SopoProject.repositories.AccountRepository;
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

    @Autowired
    private AccountRepository accountRepository;

    public boolean validateRegistrationForm(WSRegistrationForm wsRegistrationForm) throws RegistrationFormAlreadyExistsException, AccountAleadyExistsException {
        if(wsRegistrationForm.getAddress() == null){
            return false;
        } else if(wsRegistrationForm.getEmail() == null) {
            return false;
        }else if (wsRegistrationForm.getEmail() != null){
            if(accountRepository.findByEmail(wsRegistrationForm.getEmail())!=null){
                throw new AccountAleadyExistsException("Account with such email is already registered");
            }
            RegistrationForm registrationForm = registrationFormRepository.findByIdentifierNo(wsRegistrationForm.getIdentifierNo());
            if(registrationForm != null){
                throw new RegistrationFormAlreadyExistsException("User with such identifier have already send registrationForm request");
            }
            registrationForm = registrationFormRepository.findByEmail(wsRegistrationForm.getEmail());
            if(registrationForm != null){
                throw new RegistrationFormAlreadyExistsException("User with such email have already send registrationForm request");
            }
        } else if (wsRegistrationForm.getNeighbourhood() != null){
            Neighbourhood neighbourhood = neighbourhoodRepository.findByName(wsRegistrationForm.getNeighbourhood());
            return neighbourhood != null ? true : false;
        }
        if(userRepository.findUserByIdentifierNo(wsRegistrationForm.getIdentifierNo()) != null){
            throw new AccountAleadyExistsException("Account with such email is already registered");
        }
        return true;
    }

    public RegistrationFormRepository getRegistrationFormRepository() {
        return registrationFormRepository;
    }
}
