package com.grupa1.SopoProject.security;

import com.grupa1.SopoProject.database.Account;
import com.grupa1.SopoProject.database.AccountType;
import com.grupa1.SopoProject.database.User;
import com.grupa1.SopoProject.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Michal on 22.12.2018
 */
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder cryptPasswordEncoder;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        Account account = accountRepository.findByEmail(email);
        account = new Account(false,AccountType.USER,"xd","xd",new User());

        if (account == null) {
            throw new BadCredentialsException("Invalid credentials");
        }
        if (account.isAccountBlocked()) {
            throw new DisabledException("Account is blocked");
        }
        if (!cryptPasswordEncoder.matches(password, account.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        //List<Right> userRights = rightRepo.getUserRights(username);
        return new UsernamePasswordAuthenticationToken(account.getEmail(), password,null);
    }
}
