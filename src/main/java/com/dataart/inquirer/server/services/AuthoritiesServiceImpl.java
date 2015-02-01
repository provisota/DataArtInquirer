package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.AuthoritiesService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
@Service("auth")
public class AuthoritiesServiceImpl implements AuthoritiesService {
    Collection<? extends GrantedAuthority> authorities;
    Set<String> authoritiesSet = new HashSet<>();

    @Override
    public Set<String> getAuthorities() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            authoritiesSet.clear();
            authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (GrantedAuthority authority : authorities) {
                authoritiesSet.add(authority.getAuthority());
            }
            /*TODO ВАЖНО!!! Для тестирования приложения в GWT SuperDevMode
                раскоментировать следующую строку, при тестировании в деплой моде на
                томкате, или в ПРОДАКШАНЕ нужно её соответственно закоментить,
                иначе не будет работать авторизация Spring Security*/
            authoritiesSet.add("ROLE_ADMIN");
        }
        return authoritiesSet;
    }

    @Override
    public String retrieveUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            System.out.println("Not logged in");
            return null;
        } else {
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
