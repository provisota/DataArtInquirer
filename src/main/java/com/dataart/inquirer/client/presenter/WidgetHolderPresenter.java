package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.view.WidgetHolder;

/**
 * @author Alterovych Ilya
 */
public class WidgetHolderPresenter {
    private final LoginPresenter loginPresenter;
    private final UserPresenter userPresenter;
    private final AdminPresenter adminPresenter;
    private final StatisticPresenter statisticPresenter;

    private WidgetHolder holder;

    public WidgetHolderPresenter(LoginPresenter loginPresenter,
                                 UserPresenter userPresenter,
                                 AdminPresenter adminPresenter,
                                 StatisticPresenter statisticPresenter) {
        this.loginPresenter = loginPresenter;
        this.userPresenter = userPresenter;
        this.adminPresenter = adminPresenter;
        this.statisticPresenter = statisticPresenter;
    }

    public void loadProject() {
        holder = new WidgetHolder(this);
        holder.onFirstLoad();
    }

    public LoginPresenter getLoginPresenter() {
        return loginPresenter;
    }

    public UserPresenter getUserPresenter() {
        return userPresenter;
    }

    public AdminPresenter getAdminPresenter() {
        return adminPresenter;
    }

    public StatisticPresenter getStatisticPresenter() {
        return statisticPresenter;
    }
}
