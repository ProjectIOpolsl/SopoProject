package com.grupa1.SopoProject.security;

import com.grupa1.SopoProject.database.Account;
import com.grupa1.SopoProject.database.AccountType;
import com.grupa1.SopoProject.database.Privilige;
import com.grupa1.SopoProject.database.User;
import com.grupa1.SopoProject.repositories.AccountRepository;
import com.grupa1.SopoProject.repositories.PriviligeRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michal on 22.12.2018
 */

@Service
@Getter
@Setter
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PriviligeRepository priviligeRepository;

    @Override
    public org.springframework.security.core.userdetails.User
    loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if(account != null){
            AppUser appUser = new AppUser(account.getId(),account.getEmail(),account.getPassword(),account.getAccountType().toString());
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(appUser.getRole());
            return new org.springframework.security.core.userdetails.User
                    (appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }

    @Getter
    @Setter
    private static class AppUser {
        private Long id;
        private String username, password;
        private String role;

        public AppUser(Long id, String username, String password, String role) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.role = role;
        }
    }

}