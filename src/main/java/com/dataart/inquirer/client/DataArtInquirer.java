package com.dataart.inquirer.client;

import com.dataart.inquirer.client.models.AdminModel;
import com.dataart.inquirer.client.models.InquirerModel;
import com.dataart.inquirer.client.models.UserModel;
import com.dataart.inquirer.client.presenter.*;
import com.dataart.inquirer.client.services.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import java.util.HashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DataArtInquirer implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        AuthoritiesServiceAsync authServiceAsync = GWT.create(AuthoritiesService.class);
        UserServiceAsync userServiceAsync = GWT.create(UserService.class);
        InquirerServiceAsync inquirerServiceAsync = GWT.create(InquirerService.class);
        UserInquirerServiceAsync userInquirerServiceAsync =
                GWT.create(UserInquirerService.class);

        InquirerModel inquirerModel = new InquirerModel();
        /*кладём все презентеры в словарь, в котором ключь - класс реализующий интерфейс
        IPresenter, а значение объект соответствующей реализации интерфейса IPresenter
         */

        Map<Class<? extends IPresenter>, IPresenter> presenterMap = new HashMap<>();
        presenterMap.put(StartPagePresenter.class,
                new StartPagePresenter(authServiceAsync));
        presenterMap.put(UserPresenter.class, new UserPresenter(inquirerServiceAsync,
                userServiceAsync, userInquirerServiceAsync, new UserModel(),
                inquirerModel));
        presenterMap.put(AdminPresenter.class, new AdminPresenter(authServiceAsync,
                userServiceAsync, new AdminModel()));
        presenterMap.put(CreatorPresenter.class, new CreatorPresenter(authServiceAsync,
                inquirerServiceAsync, inquirerModel));
        presenterMap.put(StatisticPresenter.class, new StatisticPresenter());

        WidgetHolderPresenter widgetHolderPresenter =
                new WidgetHolderPresenter(presenterMap);
        widgetHolderPresenter.loadProject();
    }
}
