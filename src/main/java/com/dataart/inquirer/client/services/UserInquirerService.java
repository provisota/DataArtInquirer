package com.dataart.inquirer.client.services;

import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Alterovych Ilya
 */
@RemoteServiceRelativePath("springGwtServices/user_inquirer")
public interface UserInquirerService extends RemoteService {
    UserInquirerDTO addUserInquirer(UserInquirerDTO userInquirerDTO);
    UserInquirerDTO editUserInquirer(UserInquirerDTO userInquirerDTO);
    UserInquirerDTO getExistingUserInquirer(int userId, int inquirerId);
}
