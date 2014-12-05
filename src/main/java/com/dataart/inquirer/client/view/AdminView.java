package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.presenter.AdminPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.gwtbootstrap3.client.ui.Button;

/**
 * @author Alterovych Ilya
 */
public class AdminView extends Composite implements IView{

    interface adminViewUiBinder extends UiBinder<VerticalPanel, AdminView> {}
    private static adminViewUiBinder ourUiBinder = GWT.create(adminViewUiBinder.class);
    private final AdminPresenter presenter;
    @UiField
    Button users;
    @UiField
    Button addInquirer;

    @UiConstructor
    public AdminView(AdminPresenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("users")
    public void onUserButtonClick(ClickEvent event){
        //TODO получение списка юзеров и их отображенеи
    }

    @UiHandler("addInquirer")
    public void onAddInquirerClick(ClickEvent event){
        //TODO отображение формы для создания нового опросника
    }

    @Override
    public void init() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {

    }
}