package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service("auth")
public class AuthServiceImpl implements AuthService {

	@Override
	public String retrieveUsername() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication==null){
			System.out.println("Not logged in");
			return null;
		}
		else {
			return (String) authentication.getPrincipal();
		}
		
	}
	
}
