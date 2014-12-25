package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.callback.CommonAsyncCallback;
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
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public class WidgetHolder extends DockPanel {

    private Map<Class<? extends IPresenter>, IPresenter> presenterMap;

    private Button userButton;
    private Button adminButton;
    private Button creatorButton;
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
        disableAdminButtons();
    }

    private void disableAdminButtons() {
        ((AdminPresenter) presenterMap.get(AdminPresenter.class))
                .getAuthoritiesServiceAsync()
                .getAuthorities(new CommonAsyncCallback<Set<String>>() {
                    @Override
                    public void onSuccess(Set<String> adminAuthoritiesSet) {
                        if (adminAuthoritiesSet.contains("ROLE_ADMIN")) {
                            adminButton.setVisible(true);
                            creatorButton.setVisible(true);
                        }
                    }
                });
    }

    private Widget initMenuBar() {
        mainButtonsPanel = new VerticalPanel();
        mainButtonsPanel.setWidth("250px");
        mainButtonsPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        userButton = new Button("Заполнить опросник");
        userButton.setType(ButtonType.PRIMARY);
        userButton.setWidth("180px");

        adminButton = new Button("Пользователи");
        adminButton.setType(ButtonType.PRIMARY);
        adminButton.setWidth("180px");
        adminButton.setMarginBottom(10);
        adminButton.setVisible(false);

        creatorButton = new Button("Создать опросник");
        creatorButton.setType(ButtonType.PRIMARY);
        creatorButton.setWidth("180px");
        creatorButton.setMarginBottom(10);
        creatorButton.setVisible(false);

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
        mainButtonsPanel.add(creatorButton);
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

        Tooltip creatorTooltip = new Tooltip();
        creatorTooltip.setText("только для админов");
        creatorTooltip.setPlacement(Placement.RIGHT);
        creatorTooltip.add(creatorButton);

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

        creatorButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                initCenterHolder(presenterMap.get(CreatorPresenter.class).getView());
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

    public Button getAdminButton() {
        return adminButton;
    }

    public Button getCreatorButton() {
        return creatorButton;
    }
}
