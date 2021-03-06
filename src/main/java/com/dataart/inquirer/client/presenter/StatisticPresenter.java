package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.view.statistic.StatisticView;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alterovych Ilya
 */
public final class StatisticPresenter implements IPresenter {
    private StatisticView view;

    public StatisticPresenter() {
    }

    @Override
    public Widget getView() {
        initUpdateView();
        return view.asWidget();
    }

    @Override
    public void initUpdateView() {
        if (view == null) {
            //create and init view
            view = new StatisticView(this);
            view.init();
        } else {
            //update view
            view.refresh();
        }
    }
}
