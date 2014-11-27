package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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

	@Override
	public String retrieveRequestHeader(String headerName) {
		ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes)
				RequestContextHolder.currentRequestAttributes());
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return request.getHeader(headerName);
	}

}
