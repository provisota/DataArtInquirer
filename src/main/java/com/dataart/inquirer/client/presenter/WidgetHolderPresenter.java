package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.view.WidgetHolder;

import java.util.Map;

/**
 * @author Alterovych Ilya
 */
public class WidgetHolderPresenter {
    private WidgetHolder holder;
    private Map<Class<? extends IPresenter>, IPresenter> presenterMap;

    public WidgetHolderPresenter(Map<Class<? extends IPresenter>, IPresenter> presenterMap) {
        this.presenterMap = presenterMap;
    }

    public void loadProject() {
        if (holder == null) {
//            initViews();
            presenterMap.get(StartPagePresenter.class).initUpdateView();
            holder = new WidgetHolder(this);
            holder.onFirstLoad();
        }
    }

    /**
     * @deprecated
     * Метод для предварительной инициализации всех отображений.
     */
    @SuppressWarnings("UnusedDeclaration")
    private void initViews() {
        for (Class<? extends IPresenter> presenterClass : presenterMap.keySet()){
            if (presenterClass == UserPresenter.class){
                continue;
            }
            presenterMap.get(presenterClass).initUpdateView();
        }
    }

    public Map<Class<? extends IPresenter>, IPresenter> getPresenterMap() {
        return presenterMap;
    }
}
