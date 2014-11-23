package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.card.LoginCard;
import com.dataart.inquirer.client.services.AuthServiceAsync;
import com.google.gwt.user.client.ui.Widget;

public class LoginPresenter {

    private final LoginCard view = LoginCard.getInstance(this);
    private final AuthServiceAsync authServiceAsync;
//    private final ISecurityProvider securityProvider = new StubSecurityProvider();

    public LoginPresenter(AuthServiceAsync authServiceAsync) {
        this.authServiceAsync = authServiceAsync;
    }

    public void onInput(String... params) {
        //2 for username and password
        if (params == null || params.length != 2) {
            return;
        }
        //TODO обработка результатов ввода (логина и пароля)
    }

    public Widget getView() {
        return view.asWidget();
    }

    public AuthServiceAsync getAuthServiceAsync() {
        return authServiceAsync;
    }
}
