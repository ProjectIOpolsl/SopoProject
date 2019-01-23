package com.grupa1.SopoProject.service;

import com.grupa1.SopoProject.database.Neighbourhood;
import com.grupa1.SopoProject.database.RegistrationForm;
import com.grupa1.SopoProject.dto.WSRegistrationForm;
import com.grupa1.SopoProject.handlers.AccountAleadyExistsException;
import com.grupa1.SopoProject.handlers.RegistrationFormAlreadyExistsException;
import com.grupa1.SopoProject.handlers.ValidationException;
import com.grupa1.SopoProject.repositories.AccountRepository;
import com.grupa1.SopoProject.repositories.NeighbourhoodRepository;
import com.grupa1.SopoProject.repositories.RegistrationFormRepository;
import com.grupa1.SopoProject.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author Michal on 22.12.2018
 */
@Service
public class RegistrationFormService {

    private final String EMAIL_VALIDATION_REGEX=
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[" +
                    "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                    "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\" +
                    "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*" +
                    "[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b" +
                    "\\x0c\\x0e-\\x7f])+)\\])";

    @Autowired
    private RegistrationFormRepository registrationFormRepository;

    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public boolean validateRegistrationForm(WSRegistrationForm wsRegistrationForm) throws RegistrationFormAlreadyExistsException, AccountAleadyExistsException, ValidationException {
        if(StringUtils.isBlank(wsRegistrationForm.getAddress()) ){
            throw new ValidationException("Address was not provided or this field contains invalid value");
        }
        if (StringUtils.isBlank(wsRegistrationForm.getFirstName()) || StringUtils.isBlank(wsRegistrationForm.getSurname())){
            throw new ValidationException("First name or surname was not provided or this field contains invalid value");
        }
            if(StringUtils.isBlank(wsRegistrationForm.getEmail()) || !wsRegistrationForm.getEmail().matches(EMAIL_VALIDATION_REGEX)) {
            throw new ValidationException("Email was not provided or this field contains invalid value");

        } if (wsRegistrationForm.getEmail() != null){
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
        }
            if(StringUtils.isBlank(wsRegistrationForm.getFirstName()) || StringUtils.isBlank(wsRegistrationForm.getIdentifierNo())
                || StringUtils.isBlank(wsRegistrationForm.getPassword()) ){
            throw new ValidationException("Missing fields on registration form and/or identifier/login/password");

        } if(wsRegistrationForm.getIdentifierNo().isEmpty() || wsRegistrationForm.getIdentifierNo().length()!=11){
            throw new ValidationException("Id was not provider or you have passed Id with incorrect length!");
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
