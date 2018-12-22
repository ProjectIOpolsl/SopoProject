package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.dto.LoginResponse;
import com.grupa1.SopoProject.dto.WSError;
import com.grupa1.SopoProject.dto.WSLogin;
import com.grupa1.SopoProject.security.CustomUserDetailsService;
import com.grupa1.SopoProject.security.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Michal on 05.12.2018
 */

@RestController
@RequestMapping("/login")
@Api(value = "Login resource", description = "Endpoints for entering main application",
        consumes = "application/json", produces = "application/json",tags = {"Login Resource"})
public class LoginResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @ApiOperation(value = "Log into SOPO project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Correct authentication", response = LoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden acces. Your account has been blocked", response = WSError.class)
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> logIn(@RequestBody WSLogin wsLogin){
        try {
            String username = wsLogin.getUsername();
            String password = wsLogin.getPassword();

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = this.authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = this.customUserDetailsService.loadUserByUsername(username);

            List<String> roles = new ArrayList();

            for (GrantedAuthority authority : user.getAuthorities()) {
                roles.add(authority.toString());
            }

            String generatedToken = Jwts.builder()
                    .setSubject(authentication.getName())
                    .claim("authorities", authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 1000))  // in milliseconds
                    .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                    .compact();
            return new ResponseEntity<>(new LoginResponse(generatedToken,"xd"), HttpStatus.OK);

        } catch (BadCredentialsException bce) {
            WSError wsError = new WSError("Invalid token", "/login");
            return new ResponseEntity<>(wsError, HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            WSError wsError = new WSError("Unknown error occured", "/login");
            return new ResponseEntity<>(wsError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
