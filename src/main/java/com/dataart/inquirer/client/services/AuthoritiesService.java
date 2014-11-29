package com.dataart.inquirer.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Set;

/**
 * @author Alterovych Ilya
 */
@RemoteServiceRelativePath("springGwtServices/auth")
public interface AuthoritiesService extends RemoteService {
    Set<String> getAuthorities();
    String retrieveUsername();
    String retrieveRequestHeader(String headerName);
}
