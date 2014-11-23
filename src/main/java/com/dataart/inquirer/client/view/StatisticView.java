package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.presenter.StatisticPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Alterovych Ilya
 */
public class StatisticView extends Composite {

    interface StatisticViewUiBinder extends UiBinder<VerticalPanel, StatisticView> {}
    private static StatisticViewUiBinder ourUiBinder = GWT.create(StatisticViewUiBinder.class);

    private static StatisticView instance = new StatisticView();
    private StatisticPresenter presenter;

    private StatisticView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public static StatisticView getInstance(StatisticPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("StatisticView initialization failed: presenter is null");
        }
        if (instance.getPresenter() == null) {
            instance.setPresenter(presenter);
            return instance;
        } else {
            instance.setPresenter(presenter);
            return instance;
        }
    }

    public StatisticPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(StatisticPresenter presenter) {
        this.presenter = presenter;
    }
}