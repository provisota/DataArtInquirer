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

import java.util.ArrayList;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //add test user & admin records (remove for production)
        if (!checkNameExist("user") && !checkEmailExist("user@mail.com")) {
            userService.addUser(new UserDTO("user", "user@mail.com", "user", Role.ROLE_USER));
        }
        if (!checkNameExist("admin") && !checkEmailExist("admin@mail.com")) {
            userService.addUser(new UserDTO("admin", "admin@mail.com", "admin", Role.ROLE_ADMIN));
        }
        ArrayList<UserDTO> userDTOs = userService.getAll();

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserDTO existingUser = null;
        //check existing of username
        for (UserDTO userDTO : userDTOs) {
            if (userDTO.getUsername().equals(username)) {
                existingUser = userDTO;
            }
        }
        if (existingUser == null)
            throw new UsernameNotFoundException("User not found");

        String storedPass = existingUser.getPassword();

        if (!storedPass.equals(password))
            throw new BadCredentialsException("Invalid password");
        Role userRole = existingUser.getRole();
        Authentication customAuthentication =
                new CustomUserAuthentication(userRole.name(), authentication);
        customAuthentication.setAuthenticated(true);

        return customAuthentication;
    }

    private boolean checkEmailExist(String email) {
        boolean isExist = userService.findUserByEmail(email) != null;
        if (isExist) {
            System.out.println("Под эл.почтой  " + email + " уже зарегистрирован пользователь.");
        }
        return isExist;
    }

    private boolean checkNameExist(String username) {
        boolean isExist = userService.findUserByUsername(username) != null;
        if (isExist) {
            System.out.println("Под именем " + username + " уже зарегистрирован пользователь.");
        }
        return isExist;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
