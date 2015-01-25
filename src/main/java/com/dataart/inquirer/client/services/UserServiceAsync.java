package com.dataart.inquirer.client.services;

import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.Set;

@SuppressWarnings("UnusedDeclaration")
public interface UserServiceAsync {
    void getAll(AsyncCallback<ArrayList<UserDTO>> async);

    void addUser(UserDTO userDTO, AsyncCallback<UserDTO> async);

    void editUser(UserDTO userDTO, AsyncCallback<UserDTO> async);

    void addUserBatch(Set<UserDTO> userDTOs, AsyncCallback<ArrayList<UserDTO>> async);

    void findUserByUsername(String username, AsyncCallback<UserDTO> async);

    void findUserByEmail(String email, AsyncCallback<UserDTO> async);

    void addTestUsers(AsyncCallback<Void> async);

    void getLoggedInUser(AsyncCallback<UserDTO> async);

    void findUserByConfirmId(String confirmId, AsyncCallback<UserDTO> async);
}
