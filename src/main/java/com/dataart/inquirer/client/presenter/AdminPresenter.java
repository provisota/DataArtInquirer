package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.view.AdminView;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alterovych Ilya
 */
public class AdminPresenter {
    private final AdminView view = AdminView.getInstance(this);

    public AdminPresenter() {
    }

    public Widget getView() {
        return view.asWidget();
    }
}
