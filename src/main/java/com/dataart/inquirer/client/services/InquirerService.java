package com.dataart.inquirer.client.services;

import com.dataart.inquirer.shared.dto.InquirerDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

/**
 * @author Alterovych Ilya
 */
@RemoteServiceRelativePath("springGwtServices/inquirer")
public interface InquirerService extends RemoteService {
    ArrayList<InquirerDTO> getAll();
    InquirerDTO addInquirer(InquirerDTO inquirerDTO);
    InquirerDTO addTestInquirer();
    void deleteAllInquirers();
    void deleteInquirer(InquirerDTO inquirerDTO);
}
