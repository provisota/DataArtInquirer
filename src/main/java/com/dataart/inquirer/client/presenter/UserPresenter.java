package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.callback.CommonAsyncCallback;
import com.dataart.inquirer.client.models.InquirerModel;
import com.dataart.inquirer.client.models.UserModel;
import com.dataart.inquirer.client.services.InquirerServiceAsync;
import com.dataart.inquirer.client.services.UserServiceAsync;
import com.dataart.inquirer.client.view.user.UserView;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * @author Alterovych Ilya
 */
public final class UserPresenter implements IPresenter {
    private UserView view;
    private InquirerServiceAsync inquirerServiceAsync;
    private UserServiceAsync userServiceAsync;
    private UserModel userModel;
    private InquirerModel inquirerModel;

    public UserPresenter(InquirerServiceAsync inquirerServiceAsync, UserServiceAsync userServiceAsync, UserModel userModel,
                         InquirerModel inquirerModel) {

        this.inquirerServiceAsync = inquirerServiceAsync;
        this.userServiceAsync = userServiceAsync;
        this.userModel = userModel;
        this.inquirerModel = inquirerModel;
        setLoggedInUser();
    }

    /**
     * Помещает в модель юзера сущность UserDTO залогиненного юзера
     */
    private void setLoggedInUser() {
        userServiceAsync.getLoggedInUser(new CommonAsyncCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO userDTO) {
                userModel.setLoggedInUserDTO(userDTO);
            }
        });
    }

    private void updateInquirerList() {
        inquirerServiceAsync.getAll(new CommonAsyncCallback<ArrayList<InquirerDTO>>() {
            @Override
            public void onSuccess(ArrayList<InquirerDTO> inquirerDTOs) {
                inquirerModel.setInquirerDTOs(inquirerDTOs);
                userModel.getNewInquirerDTOs().clear();
                for (InquirerDTO inquirerDTO : inquirerDTOs) {
                    //TODO реализовать сортировку опросников на новые, начатые и пройденные
                    if (inquirerDTO.isPublished()) {
                        userModel.getNewInquirerDTOs().add(inquirerDTO);
                    }
                }
                view.refresh();
            }
        });
    }

    @Override
    public Widget getView() {
        updateInquirerList();
        initUpdateView();
        return view.asWidget();
    }

    @Override
    public void initUpdateView() {
        if (view == null) {
            //create and init view
            view = new UserView(this);
            updateInquirerList();
            view.init();
        } else {
            //update view
            view.refresh();
        }
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public InquirerModel getInquirerModel() {
        return inquirerModel;
    }
}
