package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.presenter.AdminPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Alterovych Ilya
 */
public class AdminView extends Composite{
    interface adminViewUiBinder extends UiBinder<VerticalPanel, AdminView> {}
    private static adminViewUiBinder ourUiBinder = GWT.create(adminViewUiBinder.class);

    private static AdminView instance = new AdminView();
    private AdminPresenter presenter;

    private AdminView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public static AdminView getInstance(AdminPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("AdminView initialization failed: presenter is null");
        }
        if (instance.getPresenter() == null) {
            instance.setPresenter(presenter);
            return instance;
        } else {
            instance.setPresenter(presenter);
            return instance;
        }
    }

    public AdminPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(AdminPresenter presenter) {
        this.presenter = presenter;
    }
}