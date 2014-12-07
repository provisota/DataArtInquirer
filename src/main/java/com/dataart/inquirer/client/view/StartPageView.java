package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.callback.CommonAsyncCallback;
import com.dataart.inquirer.client.card.LoginCard;
import com.dataart.inquirer.client.presenter.StartPagePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Alterovych Ilya
 */
public class StartPageView extends Composite implements IView{

    interface startPageViewUiBinder extends UiBinder<VerticalPanel, StartPageView> {}
    private static startPageViewUiBinder ourUiBinder = GWT.create(startPageViewUiBinder.class);
    private final StartPagePresenter presenter;
    @UiField
    Label userName;
    @UiField
    LoginCard loginCard;
    @UiField
    Label userAgent;

    @UiConstructor
    public StartPageView(StartPagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        initWidget(ourUiBinder.createAndBindUi(this));
        setUpService();
    }

    private void setUpService() {
        presenter.getAuthServiceAsync().retrieveUsername(new CommonAsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
                userName.setText("Hello " + result + "! ;)");
                loginCard.setUsernameTextBoxValue(result);
            }
        });

        presenter.getAuthServiceAsync().retrieveRequestHeader("User-Agent", new CommonAsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
                userAgent.setText("It looks like you are using " + result);
            }
        });
    }

    @Override
    public void refresh() {

    }
}