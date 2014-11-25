package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.presenter.AdminPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Alterovych Ilya
 */
public class AdminView extends Composite implements IView{

    interface adminViewUiBinder extends UiBinder<VerticalPanel, AdminView> {}
    private static adminViewUiBinder ourUiBinder = GWT.create(adminViewUiBinder.class);
    private final AdminPresenter presenter;

    @UiConstructor
    public AdminView(AdminPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {

    }
}