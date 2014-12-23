package com.dataart.inquirer.client.view.user;

import com.dataart.inquirer.client.presenter.UserPresenter;
import com.dataart.inquirer.client.view.IView;
import com.dataart.inquirer.client.view.inquirerDataGrid.InquirerDataGridWidget;
import com.dataart.inquirer.client.view.user.widgets.UserInquirerWidget;
import com.dataart.inquirer.shared.dto.InquirerDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ButtonGroup;

/**
 * @author Alterovych Ilya
 */
public class UserView extends Composite implements IView {

    interface userViewUiBinder extends UiBinder<VerticalPanel, UserView> {}
    private static userViewUiBinder ourUiBinder = GWT.create(userViewUiBinder.class);
    private final UserPresenter presenter;
    @UiField(provided = true)
    InquirerDataGridWidget dataGrid;
    @UiField
    ButtonGroup inquirerButtonGroup;
    @UiField
    Button newInquirerButton;
    @UiField
    Button unfinishedInquirerButton;
    @UiField
    Button finishedInquirerButton;
    @UiField
    HorizontalPanel selectionButtonGroup;
    @UiField
    Button startInquirerButton;
    @UiField
    VerticalPanel inquirerPanel;
    @UiField
    ButtonGroup passInquirerButtonGroup;
    @UiField
    ButtonGroup upperPassInquirerButtonGroup;
    @UiField
    Button upperGoBackButton;
    @UiField
    Button upperSaveButton;
    @UiField
    Button upperPassInquirerButton;
    @UiField
    Button goBackButton;
    @UiField
    Button saveButton;
    @UiField
    Button passInquirerButton;

    /**
     * Удаляет текуший опросник (из отображения)
     *
     * @param event клик на кнопке
     */
    @SuppressWarnings("UnusedParameters")
    @UiHandler(value = {"goBackButton", "upperGoBackButton"})
    public void onClearButton(ClickEvent event) {
        if (Window.confirm("Вы уверены? Все несохранённые данные будут потеряны!")) {
            resetInquirerPanel();
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("newInquirerButton")
    public void onShowNewInquirers(ClickEvent event){
        dataGrid.refresh(presenter.getUserModel().getNewInquirerDTOs());
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("unfinishedInquirerButton")
    public void onShowUnfinishedInquirers(ClickEvent event){
        dataGrid.refresh(presenter.getUserModel().getUnfinishedInquirerDTOs());
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("finishedInquirerButton")
    public void onShowFinishedInquirers(ClickEvent event){
        dataGrid.refresh(presenter.getUserModel().getFinishedInquirerDTOs());
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("startInquirerButton")
    public void onStartInquirer(ClickEvent event){
        if (getSelectedInquirer() == null) {
            return;
        }
        //TODO отображаем выбранный опросник
        setPassInquirerButtonGroup();
        showInquirer(getSelectedInquirer());
    }

    private void showInquirer(InquirerDTO selectedInquirer) {
        inquirerPanel.add(new UserInquirerWidget(selectedInquirer));
    }

    private void resetInquirerPanel() {
        presenter.initUpdateView();

        passInquirerButtonGroup.setVisible(false);
        upperPassInquirerButtonGroup.setVisible(false);

        startInquirerButton.setVisible(true);
        selectionButtonGroup.setVisible(true);
        dataGrid.setVisible(true);
        dataGrid.resetSelection();
        inquirerPanel.clear();
    }

    private void setPassInquirerButtonGroup() {
        passInquirerButtonGroup.setVisible(true);
        upperPassInquirerButtonGroup.setVisible(true);

        startInquirerButton.setVisible(false);
        selectionButtonGroup.setVisible(false);
        dataGrid.setVisible(false);
    }

    private InquirerDTO getSelectedInquirer() {
        InquirerDTO inquirerDTO = presenter.getInquirerModel().getSelectedInquirerDTO();
        if (inquirerDTO == null) {
            Window.alert("Сначала выберите существующий опросник из базы данных");
            return null;
        }
        return inquirerDTO;
    }

    @UiConstructor
    public UserView(UserPresenter presenter) {
        this.presenter = presenter;
        dataGrid = new InquirerDataGridWidget(presenter.getInquirerModel());
    }

    @Override
    public void init() {
        dataGrid.init();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {
        dataGrid.refresh(presenter.getUserModel().getNewInquirerDTOs());
    }
}