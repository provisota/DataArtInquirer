package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.callback.CommonAsyncCallback;
import com.dataart.inquirer.client.models.CreatorModel;
import com.dataart.inquirer.client.services.AuthoritiesServiceAsync;
import com.dataart.inquirer.client.view.AccessDeniedView;
import com.dataart.inquirer.client.view.CreatorView;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public final class CreatorPresenter implements IPresenter {
    private CreatorView view;
    private AuthoritiesServiceAsync authServiceAsync;
    private CreatorModel model;
    private Set<String> authoritiesSet = new HashSet<>();

    public CreatorPresenter(AuthoritiesServiceAsync authServiceAsync, CreatorModel model) {
        this.authServiceAsync = authServiceAsync;
        this.model = model;
        updateAuthorities();
    }

    private void updateAuthorities() {
        authServiceAsync.getAuthorities(new CommonAsyncCallback<Set<String>>() {
            @Override
            public void onSuccess(Set<String> result) {
                authoritiesSet = result;
//                Window.confirm("authoritiesSet: " + result);
            }
        });
    }

    @Override
    public Widget getView() {
        /*TODO ВАЖНО!!! для получения доступа к функциям админа в GWTSuperDevMode
        раскоментировать следующую строку, при тестировании в деплой моде на
        томкате нужно её соответственно закоментить, иначе не будет работать
        авторизация Spring Security*/
        authoritiesSet.add("ROLE_ADMIN");
        if (authoritiesSet.contains("ROLE_ADMIN")) {
            //TODO updateInquirerList();
            return view.asWidget();
        } else {
            return AccessDeniedView.getInstance().asWidget();
        }
    }

    @Override
    public void initUpdateView() {
        if (view == null) {
            //create and init view
            view = new CreatorView(this);
            view.init();
        } else {
            //update view
            view.refresh();
        }
    }

    public CreatorModel getModel() {
        return model;
    }

    public void setModel(CreatorModel model) {
        this.model = model;
    }
}
