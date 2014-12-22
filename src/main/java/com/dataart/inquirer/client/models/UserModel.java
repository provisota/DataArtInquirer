package com.dataart.inquirer.client.models;

import com.dataart.inquirer.shared.dto.InquirerDTO;
import com.dataart.inquirer.shared.dto.UserDTO;

import java.util.ArrayList;

/**
 * @author Alterovych Ilya
 */
public class UserModel {

    private UserDTO loggedInUserDTO;
    private ArrayList<InquirerDTO> newInquirerDTOs = new ArrayList<>();
    private ArrayList<InquirerDTO> unfinishedInquirerDTOs = new ArrayList<>();
    private ArrayList<InquirerDTO> finishedInquirerDTOs = new ArrayList<>();

    public ArrayList<InquirerDTO> getNewInquirerDTOs() {
        return newInquirerDTOs;
    }

    public void setNewInquirerDTOs(ArrayList<InquirerDTO> newInquirerDTOs) {
        this.newInquirerDTOs = newInquirerDTOs;
    }

    public ArrayList<InquirerDTO> getUnfinishedInquirerDTOs() {
        return unfinishedInquirerDTOs;
    }

    public void setUnfinishedInquirerDTOs(ArrayList<InquirerDTO> unfinishedInquirerDTOs) {
        this.unfinishedInquirerDTOs = unfinishedInquirerDTOs;
    }

    public ArrayList<InquirerDTO> getFinishedInquirerDTOs() {
        return finishedInquirerDTOs;
    }

    public void setFinishedInquirerDTOs(ArrayList<InquirerDTO> finishedInquirerDTOs) {
        this.finishedInquirerDTOs = finishedInquirerDTOs;
    }

    public UserDTO getLoggedInUserDTO() {
        return loggedInUserDTO;
    }

    public void setLoggedInUserDTO(UserDTO loggedInUserDTO) {
        this.loggedInUserDTO = loggedInUserDTO;
    }
}
