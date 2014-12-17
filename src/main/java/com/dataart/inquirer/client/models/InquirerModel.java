package com.dataart.inquirer.client.models;

import com.dataart.inquirer.shared.dto.InquirerDTO;

import java.util.ArrayList;

/**
 * @author Alterovych Ilya
 */
@SuppressWarnings("UnusedDeclaration")
public class InquirerModel {
    private ArrayList<InquirerDTO> inquirerDTOs;
    private InquirerDTO selectedInquirerDTO;

    public ArrayList<InquirerDTO> getInquirerDTOs() {
        return inquirerDTOs;
    }

    public void setInquirerDTOs(ArrayList<InquirerDTO> inquirerDTOs) {
        this.inquirerDTOs = inquirerDTOs;
    }

    public InquirerDTO getSelectedInquirerDTO() {
        return selectedInquirerDTO;
    }

    public void setSelectedInquirerDTO(InquirerDTO selectedInquirerDTO) {
        this.selectedInquirerDTO = selectedInquirerDTO;
    }
}
