package com.dataart.inquirer.server.auth;

import com.dataart.inquirer.client.services.UserService;
import com.dataart.inquirer.shared.dto.UserDTO;
import com.dataart.inquirer.shared.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //add test user & admin records (remove for production)
        userService.addTestUsers();

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        //check existing of username
        UserDTO existingUser = userService.findUserByUsername(username);
        if (existingUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String storedPass = existingUser.getPassword();

        if (!storedPass.equals(password))
            throw new BadCredentialsException("Invalid password");
        Role userRole = existingUser.getRole();
        Authentication customAuthentication =
                new CustomUserAuthentication(userRole.name(), authentication);
        customAuthentication.setAuthenticated(true);

        return customAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
