package com.dataart.inquirer.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("springGwtServices/auth")
public interface AuthService extends RemoteService {
	String retrieveUsername();
	String retrieveRequestHeader(String headerName);
}
