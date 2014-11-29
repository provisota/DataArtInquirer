package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.services.AuthoritiesServiceAsync;
import com.dataart.inquirer.client.view.StartPageView;
import com.google.gwt.user.client.ui.Widget;

public final class StartPagePresenter implements IPresenter {
    private StartPageView view;
    private final AuthoritiesServiceAsync authServiceAsync;

    public StartPagePresenter(AuthoritiesServiceAsync authServiceAsync) {
        this.authServiceAsync = authServiceAsync;
    }

    public void onInput(String... params) {
        //2 for username and password
        if (params == null || params.length != 2) {
            return;
        }
        //TODO обработка результатов ввода (логина и пароля)
    }

    @Override
    public Widget getView() {
        return view.asWidget();
    }

    @Override
    public void initUpdateView() {
        if (view == null) {
            //create and init view
            view = new StartPageView(this);
            view.init();
        } else {
            //update view
            view.refresh();
        }
    }

    public AuthoritiesServiceAsync getAuthServiceAsync() {
        return authServiceAsync;
    }
}
