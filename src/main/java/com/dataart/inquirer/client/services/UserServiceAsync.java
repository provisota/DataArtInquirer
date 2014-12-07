package com.dataart.inquirer.client.services;

import com.dataart.inquirer.shared.dto.UserDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.Set;

public interface UserServiceAsync {
    void getAll(AsyncCallback<ArrayList<UserDTO>> async);

    void addUser(UserDTO userDTO, AsyncCallback<UserDTO> async);

    void editUser(UserDTO userDTO, AsyncCallback<UserDTO> async);

    void addUserBatch(Set<UserDTO> userDTOs, AsyncCallback<ArrayList<UserDTO>> async);
}
