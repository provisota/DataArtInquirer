package com.dataart.inquirer.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Set;

public interface AuthoritiesServiceAsync {
    void getAuthorities(AsyncCallback<Set<String>> async);
    void retrieveUsername(AsyncCallback<String> async);
    void retrieveRequestHeader(String headerName, AsyncCallback<String> async);
}
