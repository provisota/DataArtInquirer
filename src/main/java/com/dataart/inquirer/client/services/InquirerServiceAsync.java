package com.dataart.inquirer.client.services;

import com.dataart.inquirer.shared.dto.InquirerDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

public interface InquirerServiceAsync {
    void getAll(AsyncCallback<ArrayList<InquirerDTO>> async);

    void addInquirer(InquirerDTO inquirerDTO, AsyncCallback<InquirerDTO> async);

    void addTestInquirer(AsyncCallback<InquirerDTO> async);

    void deleteAllInquirers(AsyncCallback<Void> async);

    void deleteInquirer(InquirerDTO inquirerDTO, AsyncCallback<Void> async);
}
