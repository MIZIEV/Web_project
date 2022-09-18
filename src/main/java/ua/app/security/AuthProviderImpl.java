package ua.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ua.app.services.UserDetailService;

import java.util.Collections;

public class AuthProviderImpl implements AuthenticationProvider {

    private final UserDetailService userDetailService;

    @Autowired
    public AuthProviderImpl(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        UserDetails userDetails = userDetailService.loadUserByUsername(name);

        String password = authentication.getCredentials().toString();
        password.equals(userDetails.getPassword());
        if(!password.equals(userDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password");


        return new UsernamePasswordAuthenticationToken(userDetails,password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}