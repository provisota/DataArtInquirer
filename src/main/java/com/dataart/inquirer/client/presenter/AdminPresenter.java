package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.view.AdminView;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alterovych Ilya
 */
public class AdminPresenter implements IPresenter {
    private AdminView view;

    public AdminPresenter() {
    }

    @Override
    public Widget getView() {
        return view.asWidget();
    }

    @Override
    public void initUpdateView() {
        if (view == null) {
            //create and init view
            view = new AdminView(this);
            view.init();
        } else {
            //update view
            view.refresh();
        }
    }
}
