package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.view.StatisticView;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alterovych Ilya
 */
public class StatisticPresenter {
    private final StatisticView view = StatisticView.getInstance(this);

    public StatisticPresenter() {
    }

    public Widget getView() {
        return view.asWidget();
    }
}
