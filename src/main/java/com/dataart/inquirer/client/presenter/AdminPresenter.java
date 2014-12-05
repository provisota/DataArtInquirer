package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.services.AuthoritiesServiceAsync;
import com.dataart.inquirer.client.view.AccessDeniedView;
import com.dataart.inquirer.client.view.AdminView;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public final class AdminPresenter implements IPresenter {
    private AdminView view;
    private AuthoritiesServiceAsync authoritiesServiceAsync;
    private Set<String> authoritiesSet = new HashSet<>();

    public AdminPresenter(AuthoritiesServiceAsync authoritiesServiceAsync) {
        this.authoritiesServiceAsync = authoritiesServiceAsync;
        updateAuthorities();
    }

    private void updateAuthorities() {
        authoritiesServiceAsync.getAuthorities(new AsyncCallback<Set<String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("ERROR: " + throwable.getMessage());
            }

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
            view = new AdminView(this);
            view.init();
        } else {
            //update view
            view.refresh();
        }
    }
}
