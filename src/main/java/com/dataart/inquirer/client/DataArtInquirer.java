package com.dataart.inquirer.client;

import com.dataart.inquirer.client.presenter.*;
import com.dataart.inquirer.client.services.AuthService;
import com.dataart.inquirer.client.services.AuthServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DataArtInquirer implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        AuthServiceAsync authServiceAsync = GWT.create(AuthService.class);

        LoginPresenter loginPresenter = new LoginPresenter(authServiceAsync);
        UserPresenter userPresenter = new UserPresenter();
        AdminPresenter adminPresenter = new AdminPresenter();
        StatisticPresenter statisticPresenter = new StatisticPresenter();

        WidgetHolderPresenter widgetHolderPresenter =
                new WidgetHolderPresenter(loginPresenter, userPresenter,
                        adminPresenter, statisticPresenter);
        widgetHolderPresenter.loadProject();
    }
}
