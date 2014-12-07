package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.callback.CommonAsyncCallback;
import com.dataart.inquirer.client.models.AdminModel;
import com.dataart.inquirer.client.services.AuthoritiesServiceAsync;
import com.dataart.inquirer.client.services.UserServiceAsync;
import com.dataart.inquirer.client.view.AccessDeniedView;
import com.dataart.inquirer.client.view.AdminView;
import com.dataart.inquirer.shared.dto.UserDTO;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public final class AdminPresenter implements IPresenter {
    private AdminView view;
    private AuthoritiesServiceAsync authoritiesServiceAsync;
    private UserServiceAsync userServiceAsync;
    private AdminModel model;
    private Set<String> authoritiesSet = new HashSet<>();

    public AdminPresenter(AuthoritiesServiceAsync authoritiesServiceAsync,
                          UserServiceAsync userServiceAsync, AdminModel model) {
        this.authoritiesServiceAsync = authoritiesServiceAsync;
        this.userServiceAsync = userServiceAsync;
        this.model = model;
        updateAuthorities();
    }

    public void updateUserList() {
        userServiceAsync.getAll(new CommonAsyncCallback<ArrayList<UserDTO>>() {
            @Override
            public void onSuccess(ArrayList<UserDTO> userDTOs) {
                model.setUserDTOs(userDTOs);
                initUpdateView();
            }
        });
    }

    public void updateUserRoles(Set<UserDTO> userDTOs) {
        userServiceAsync.addUserBatch(userDTOs, new CommonAsyncCallback<ArrayList<UserDTO>>() {
            @Override
            public void onSuccess(ArrayList<UserDTO> result) {
                updateUserList();
                initUpdateView();
            }
        });
    }

    private void updateAuthorities() {
        authoritiesServiceAsync.getAuthorities(new CommonAsyncCallback<Set<String>>() {
            @Override
            public void onSuccess(Set<String> result) {
                authoritiesSet = result;
//                Window.confirm("authoritiesSet: " + result);
            }
        });
        /*TODO для получения доступа к функциям админа в GWTSuperDevMode
        раскоментировать следующую строку, при тестировании в деплой моде на
        томкате нужно её соответственно закоментить, иначе не будет работать
        авторизация Spring Security*/
        authoritiesSet.add("ROLE_ADMIN");
    }

    @Override
    public Widget getView() {
        updateAuthorities();
        if (authoritiesSet.contains("ROLE_ADMIN")) {
            return view.asWidget();
        } else {
            return AccessDeniedView.getInstance().asWidget();
        }
    }

    @Override
    public void initUpdateView() {
        if (view == null) {
            //create and init view
            updateUserList();
            view = new AdminView(this);
            view.init();
        } else {
            //update view
            view.refresh();
        }
    }

    public AdminModel getModel() {
        return model;
    }

    public void setModel(AdminModel model) {
        this.model = model;
    }
}
