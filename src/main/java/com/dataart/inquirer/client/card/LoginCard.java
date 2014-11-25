package com.dataart.inquirer.client.card;

import com.dataart.inquirer.client.presenter.StartPagePresenter;
import com.dataart.inquirer.shared.utils.RegExpPatterns;
import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

@SuppressWarnings("all")
public class LoginCard extends Composite {

    protected interface LoginCardUiBinder extends UiBinder<Form, LoginCard> {}
    private static LoginCardUiBinder ourUiBinder = GWT.create(LoginCardUiBinder.class);

    private static LoginCard instance = new LoginCard();
    private StartPagePresenter presenter;

    private LoginCard() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public static LoginCard getInstance(StartPagePresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("LoginCard initialization failed: presenter is null");
        }
        if (instance.getPresenter() == null) {
            instance.setPresenter(presenter);
            return instance;
        } else {
            instance.clearState();
            instance.setPresenter(presenter);
            return instance;
        }
    }

    @UiField
    protected Fieldset controlFieldSet;
    @UiField
    protected ControlGroup usernameGroup;
    @UiField
    protected TextBox username;
    @UiField
    protected ControlGroup passwordGroup;
    @UiField
    protected PasswordTextBox password;
    @UiField
    protected Button loginButton;
    @UiField
    protected Button logoutButton;

    @UiHandler("loginButton")
    public void onLoginClicked(ClickEvent event) {
        presenter.onInput(username.getText(), password.getText());
    }

    @UiHandler("logoutButton")
    public void onLogoutClicked(ClickEvent event) {
        Window.Location.assign("j_spring_security_logout");
    }

    @UiHandler("username")
    public void onUsernameFocused(FocusEvent event) {
        usernameGroup.setType(ControlGroupType.NONE);
    }

    @UiHandler("username")
    public void onUsernameBlur(BlurEvent event) {
        if (!username.getValue().matches(RegExpPatterns.LOGIN_PATTERN)) {
            usernameGroup.setType(ControlGroupType.ERROR);
        }
    }

    @UiHandler("password")
    public void onPasswordFocus(FocusEvent event) {
        passwordGroup.setType(ControlGroupType.NONE);
    }

    @UiHandler("password")
    public void onPasswordBlur(BlurEvent event) {
        if (!password.getValue().matches(RegExpPatterns.PASSWORD_PATTERN)) {
            passwordGroup.setType(ControlGroupType.ERROR);
        }
    }

    @UiHandler(value = {"username", "password"})
    public void onEnterPressed(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            presenter.onInput(username.getText(), password.getText());
        }
    }

    public void alert() {
        usernameGroup.setType(ControlGroupType.ERROR);
        passwordGroup.setType(ControlGroupType.ERROR);
    }

    public StartPagePresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(StartPagePresenter presenter) {
        this.presenter = presenter;
    }

    public void clearState() {
        //nothing to do, couse login page don't have widgets state after submit;
    }

    public void setUsernameTextBoxValue(String username) {
        this.username.setText(username);
    }
}