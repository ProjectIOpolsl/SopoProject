package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.dto.WSLoginResponse;
import com.grupa1.SopoProject.dto.WSError;
import com.grupa1.SopoProject.dto.WSLogin;
import com.grupa1.SopoProject.handlers.AccountBlockedException;
import com.grupa1.SopoProject.handlers.NoSuchUserRegistered;
import com.grupa1.SopoProject.security.CustomUserDetailsService;
import com.grupa1.SopoProject.security.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

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
    private TokenUtil tokenUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @ApiOperation(value = "Log into SOPO project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Correct authentication", response = WSLoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden acces. Your account has been blocked", response = WSError.class)
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> logIn(@RequestBody WSLogin wsLogin){
        try {
            String username = wsLogin.getUsername() != null ? wsLogin.getUsername() : "";
            String password = wsLogin.getPassword() != null ? wsLogin.getPassword() : "";

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = this.authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = this.customUserDetailsService.loadUserByUsername(username);

            List<String> roles = new ArrayList();

            for (GrantedAuthority authority : user.getAuthorities()) {
                roles.add(authority.toString());
            }

            String generatedToken = tokenUtil.generateToken(authentication);
            return new ResponseEntity<>(new WSLoginResponse(generatedToken,"NOT_AVALIABLE_YET"), HttpStatus.OK);

        } catch (BadCredentialsException bce) {
            WSError wsError = new WSError("Invalid credentials", "/login");
            return new ResponseEntity<>(wsError, HttpStatus.FORBIDDEN);

        } catch (AccountBlockedException | NoSuchUserRegistered exc) {
            WSError wsError = new WSError(exc.getMessage(), "/login");
            return new ResponseEntity<>(wsError, HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            WSError wsError = new WSError("Unknown error occured", "/login");
            return new ResponseEntity<>(wsError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
