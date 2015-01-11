package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.callback.CommonAsyncCallback;
import com.dataart.inquirer.client.models.InquirerModel;
import com.dataart.inquirer.client.models.UserModel;
import com.dataart.inquirer.client.services.InquirerServiceAsync;
import com.dataart.inquirer.client.services.UserInquirerServiceAsync;
import com.dataart.inquirer.client.services.UserServiceAsync;
import com.dataart.inquirer.client.view.user.UserView;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * @author Alterovych Ilya
 */
public final class UserPresenter implements IPresenter {
    private UserView view;
    private InquirerServiceAsync inquirerServiceAsync;
    private UserServiceAsync userServiceAsync;
    private UserInquirerServiceAsync userInquirerServiceAsync;
    private UserModel userModel;
    private InquirerModel inquirerModel;

    public UserPresenter(InquirerServiceAsync inquirerServiceAsync,
                         UserServiceAsync userServiceAsync,
                         UserInquirerServiceAsync userInquirerServiceAsync,
                         UserModel userModel,
                         InquirerModel inquirerModel) {

        this.inquirerServiceAsync = inquirerServiceAsync;
        this.userServiceAsync = userServiceAsync;
        this.userInquirerServiceAsync = userInquirerServiceAsync;
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

    public void saveUnfinishedUserInquirer() {
        getUserInquirerIfExists(false);
    }

    public void saveFinishedUserInquirer() {
        getUserInquirerIfExists(true);
    }

    public void getUserInquirerIfExists(final boolean isFinished) {
        userInquirerServiceAsync.getExistingUserInquirer(
                userModel.getLoggedInUserDTO().getId(),
                inquirerModel.getSelectedInquirerDTO().getId(),
                new CommonAsyncCallback<UserInquirerDTO>() {
                    @Override
                    public void onSuccess(UserInquirerDTO result) {
//                        Window.confirm(String.valueOf("уже существующий в БД опросник\n" +
//                                result));
                        if (result != null) {
                            result.setFinished(isFinished);
                            addUserInquirer(view.createUserInquirerDTO(result));
                        } else {
                            addUserInquirer(view.createUserInquirerDTO(isFinished));
                        }
                    }
                });
    }

    private void addUserInquirer(final UserInquirerDTO userInquirerDTO) {
//        Window.confirm(String.valueOf("созданный опросник\n" +
//                userInquirerDTO));
        userInquirerServiceAsync.addUserInquirer(userInquirerDTO,
                new CommonAsyncCallback<UserInquirerDTO>() {
                    @Override
                    public void onSuccess(UserInquirerDTO result) {
//                        Window.confirm(String.valueOf("сохранённый опросник\n" +
//                                result));
                        view.resetInquirerPanel();
                        initUpdateView();
                    }
                });
    }

    private void updateInquirerList() {
        inquirerServiceAsync.getAll(new CommonAsyncCallback<ArrayList<InquirerDTO>>() {
            @Override
            public void onSuccess(ArrayList<InquirerDTO> inquirerDTOs) {

                userModel.getNewInquirerDTOs().clear();
                userModel.getUnfinishedInquirerDTOs().clear();
                userModel.getFinishedInquirerDTOs().clear();

                for (InquirerDTO inquirerDTO : inquirerDTOs) {
                    if (inquirerDTO.isPublished()) {
                        if (isInquirerUnfinishedForThisUser(inquirerDTO,
                                userModel.getLoggedInUserDTO())) {
                            userModel.getUnfinishedInquirerDTOs().add(inquirerDTO);
                        } else if (isInquirerFinishedForThisUser(inquirerDTO,
                                userModel.getLoggedInUserDTO())) {
                            userModel.getFinishedInquirerDTOs().add(inquirerDTO);
                        } else {
                            userModel.getNewInquirerDTOs().add(inquirerDTO);
                        }
                    }
                }
                inquirerModel.setInquirerDTOs(userModel.getNewInquirerDTOs());
                view.refresh();
            }
        });
    }

    private boolean isInquirerFinishedForThisUser(InquirerDTO inquirerDTO,
                                                  UserDTO loggedInUserDTO) {
        for (UserInquirerDTO userInquirerDTO : inquirerDTO.getUserInquirerList()) {
            if (userInquirerDTO.getUserDTO().equals(loggedInUserDTO) &&
                    userInquirerDTO.isFinished()) {
                return true;
            }
        }
        return false;
    }

    private boolean isInquirerUnfinishedForThisUser(InquirerDTO inquirerDTO,
                                                    UserDTO loggedInUserDTO) {
        for (UserInquirerDTO userInquirerDTO : inquirerDTO.getUserInquirerList()) {
            if (userInquirerDTO.getUserDTO().equals(loggedInUserDTO) &&
                    !userInquirerDTO.isFinished()) {
                return true;
            }
        }
        return false;
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
            updateInquirerList();
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
