package com.dataart.inquirer.client.models;

import com.dataart.inquirer.shared.dto.user.UserDTO;

import java.util.ArrayList;

/**
 * @author Alterovych Ilya
 */
@SuppressWarnings("UnusedDeclaration")
public class AdminModel {
    private ArrayList<UserDTO> userDTOs;
    private UserDTO selectedUserDTO;

    public ArrayList<UserDTO> getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(ArrayList<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
    }

    public UserDTO getSelectedUserDTO() {
        return selectedUserDTO;
    }

    public void setSelectedUserDTO(UserDTO selectedUserDTO) {
        this.selectedUserDTO = selectedUserDTO;
    }
}
