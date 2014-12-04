package com.dataart.inquirer.server.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static Map<String, String> users = new HashMap<>();
	private static Map<String, String> userRoles = new HashMap<>();

	static {
        users.put("admin", "admin");
        users.put("user", "user");

		userRoles.put("admin", "ROLE_ADMIN");
		userRoles.put("user", "ROLE_USER");
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

		if (users.get(username)==null)
			throw new UsernameNotFoundException("User not found");

		String storedPass = users.get(username);

		if (!storedPass.equals(password))
			throw new BadCredentialsException("Invalid password");
		String userRole = userRoles.get(username);
		Authentication customAuthentication = new CustomUserAuthentication(userRole, authentication);
		customAuthentication.setAuthenticated(true);

		return customAuthentication;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
