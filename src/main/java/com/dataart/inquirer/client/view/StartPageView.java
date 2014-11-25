package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.card.LoginCard;
import com.dataart.inquirer.client.presenter.StartPagePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
        presenter.getAuthServiceAsync().retrieveUsername(new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                userName.setText("Remote Procedure Call - Failure");
                loginCard.setUsernameTextBoxValue("Remote Procedure Call - Failure");
            }

            public void onSuccess(String result) {
                userName.setText("Hello " + result + "! ;)");
                loginCard.setUsernameTextBoxValue(result);
            }
        });
    }

    @Override
    public void refresh() {

    }
}