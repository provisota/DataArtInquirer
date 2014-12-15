package com.dataart.inquirer.client.view.statistic;

import com.dataart.inquirer.client.presenter.StatisticPresenter;
import com.dataart.inquirer.client.view.IView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Alterovych Ilya
 */
public class StatisticView extends Composite implements IView {

    interface statisticViewUiBinder extends UiBinder<VerticalPanel, StatisticView> {}
    private static statisticViewUiBinder ourUiBinder = GWT.create(statisticViewUiBinder.class);
    private final StatisticPresenter presenter;

    @UiConstructor
    public StatisticView(StatisticPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {

    }
}