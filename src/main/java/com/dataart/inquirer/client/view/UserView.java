package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.presenter.UserPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Alterovych Ilya
 */
public class UserView extends Composite {

    interface UserViewUiBinder extends UiBinder<VerticalPanel, UserView> {}
    private static UserViewUiBinder ourUiBinder = GWT.create(UserViewUiBinder.class);

    private static UserView instance = new UserView();
    private UserPresenter presenter;

    private UserView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public static UserView getInstance(UserPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("UserView initialization failed: presenter is null");
        }
        if (instance.getPresenter() == null) {
            instance.setPresenter(presenter);
            return instance;
        } else {
            instance.setPresenter(presenter);
            return instance;
        }
    }

    public UserPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(UserPresenter presenter) {
        this.presenter = presenter;
    }
}