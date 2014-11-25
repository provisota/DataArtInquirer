package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.view.UserView;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alterovych Ilya
 */
public class UserPresenter implements IPresenter {
    private UserView view;

    public UserPresenter() {
    }

    @Override
    public Widget getView() {
        return view.asWidget();
    }

    @Override
    public void initUpdateView() {
        if (view == null) {
            //create and init view
            view = new UserView(this);
            view.init();
        } else {
            //update view
            view.refresh();
        }
    }
}
