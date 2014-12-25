package com.dataart.inquirer.client.services;

import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserInquirerServiceAsync {
    void addUserInquirer(UserInquirerDTO userInquirerDTO,
                         AsyncCallback<UserInquirerDTO> async);

    void editUserInquirer(UserInquirerDTO userInquirerDTO,
                          AsyncCallback<UserInquirerDTO> async);

    void getExistingUserInquirer(int userId, int inquirerId,
                                 AsyncCallback<UserInquirerDTO> async);
}
