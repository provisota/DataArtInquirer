package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.presenter.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Tooltip;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.Placement;

import java.util.Map;

/**
 * @author Alterovych Ilya
 */
public class WidgetHolder extends DockPanel {

    private Map<Class<? extends IPresenter>, IPresenter> presenterMap;

    private Button userButton;
    private Button adminButton;
    private Button statisticButton;
    private Button logoutButton;

    private VerticalPanel mainButtonsPanel;

    private VerticalPanel centerHolder = new VerticalPanel();

    public WidgetHolder(WidgetHolderPresenter widgetHolderPresenter) {
        this.presenterMap = widgetHolderPresenter.getPresenterMap();

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        horizontalPanel.setHeight("100%");
        horizontalPanel.add(initMenuBar());
        horizontalPanel.add(initCenterHolder(presenterMap.get(StartPagePresenter.class).getView()));
        RootPanel.get().add(this);
        setSize("100%", "100%");
        add(new HTML("<p></p>"), DockPanel.NORTH);
        add(horizontalPanel, DockPanel.CENTER);
        setCellHorizontalAlignment(horizontalPanel, HasHorizontalAlignment.ALIGN_CENTER);
        setCellVerticalAlignment(horizontalPanel, HasVerticalAlignment.ALIGN_MIDDLE);
        setUpHandlers();
    }

    public void onFirstLoad() {
        initCenterHolder(presenterMap.get(StartPagePresenter.class).getView());
    }

    private Widget initMenuBar() {
        mainButtonsPanel = new VerticalPanel();
        mainButtonsPanel.setWidth("250px");
        mainButtonsPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        userButton = new Button("Заполнить опросник");
        userButton.setType(ButtonType.PRIMARY);
        userButton.setWidth("180px");


        adminButton = new Button("Добавить опросник");
        adminButton.setType(ButtonType.PRIMARY);
        adminButton.setWidth("180px");

        statisticButton = new Button("Статистика (Графики)");
        statisticButton.setType(ButtonType.PRIMARY);
        statisticButton.setWidth("180px");

        logoutButton = new Button("Выйти");
        logoutButton.setType(ButtonType.PRIMARY);
        logoutButton.setWidth("180px");

        addTooltips();

        mainButtonsPanel.add(new HTML("<p></p>"));
        mainButtonsPanel.add(userButton);
        mainButtonsPanel.add(new HTML("<p></p>"));
        mainButtonsPanel.add(adminButton);
        mainButtonsPanel.add(new HTML("<p></p>"));
        mainButtonsPanel.add(statisticButton);
        mainButtonsPanel.add(new HTML("<p></p>"));
        mainButtonsPanel.add(logoutButton);

        return mainButtonsPanel;
    }

    private void addTooltips() {
        Tooltip adminTooltip = new Tooltip();
        adminTooltip.setText("только для админов");
        adminTooltip.setPlacement(Placement.RIGHT);
        adminTooltip.add(adminButton);

        Tooltip logoutTooltip = new Tooltip();
        logoutTooltip.setText("завершить сеанс");
        logoutTooltip.setPlacement(Placement.BOTTOM);
        logoutTooltip.add(logoutButton);
    }

    private void setUpHandlers() {
        userButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                initCenterHolder(presenterMap.get(UserPresenter.class).getView());
            }
        });
        adminButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                initCenterHolder(presenterMap.get(AdminPresenter.class).getView());
            }
        });
        statisticButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                initCenterHolder(presenterMap.get(StatisticPresenter.class).getView());
            }
        });
        logoutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Window.Location.assign("j_spring_security_logout");
            }
        });
    }

    private Widget initCenterHolder(IsWidget widget) {
        centerHolder.clear();
        centerHolder.add(widget);
        centerHolder.setCellVerticalAlignment(widget, HasVerticalAlignment.ALIGN_MIDDLE);
        return centerHolder;
    }
}
