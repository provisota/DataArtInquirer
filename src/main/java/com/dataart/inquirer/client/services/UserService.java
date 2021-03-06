package com.dataart.inquirer.client.services;

import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
@RemoteServiceRelativePath("springGwtServices/user")
public interface UserService extends RemoteService {
    ArrayList<UserDTO> getAll();

    UserDTO editUser(UserDTO userDTO);

    UserDTO addUser(UserDTO userDTO);

    ArrayList<UserDTO> addUserBatch(Set<UserDTO> userDTOs);

    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email);

    UserDTO findUserByConfirmId(String confirmId);

    void addTestUsers();

    UserDTO getLoggedInUser();
}
