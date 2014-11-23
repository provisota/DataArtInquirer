package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.view.UserView;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alterovych Ilya
 */
public class UserPresenter {
    private final UserView view = UserView.getInstance(this);

    public UserPresenter() {
    }

    public Widget getView() {
        return view.asWidget();
    }
}
