package com.dataart.inquirer.client.view.creator;

import com.dataart.inquirer.client.presenter.CreatorPresenter;
import com.dataart.inquirer.client.view.IView;
import com.dataart.inquirer.client.view.InquirerDataGrid.InquirerDataGridWidget;
import com.dataart.inquirer.client.view.creator.widgets.CreateAnswerWidget;
import com.dataart.inquirer.client.view.creator.widgets.CreateInquirerWidget;
import com.dataart.inquirer.client.view.creator.widgets.CreateQuestionWidget;
import com.dataart.inquirer.shared.dto.AnswerDTO;
import com.dataart.inquirer.shared.dto.InquirerDTO;
import com.dataart.inquirer.shared.dto.QuestionDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;

/**
 * @author Alterovych Ilya
 */
public class CreatorView extends Composite implements IView {

    interface creatorViewUiBinder extends UiBinder<VerticalPanel, CreatorView> {
    }

    private static creatorViewUiBinder ourUiBinder = GWT.create(creatorViewUiBinder.class);
    private final CreatorPresenter presenter;
    @UiField
    Button addInquirerButton;
    @UiField
    Button saveInquirer;
    @UiField
    VerticalPanel inquirerPanel;
    @UiField
    Button clearInquirerButton;
    @UiField(provided = true)
    InquirerDataGridWidget dataGrid;
    @UiField
    Button deleteInquirerButton;
    @UiField
    Button editInquirerButton;

    @SuppressWarnings("UnusedParameters")
    @UiHandler("deleteInquirerButton")
    public void onDeleteInquirer(ClickEvent event) {
        presenter.deleteSelectedInquirer();
        resetInquirerPanel();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("addInquirerButton")
    public void onAddInquirer(ClickEvent event) {
        saveInquirer.setVisible(true);
        clearInquirerButton.setVisible(true);
        addInquirerButton.setEnabled(false);
        deleteInquirerButton.setVisible(false);
        editInquirerButton.setVisible(false);
        inquirerPanel.add(new CreateInquirerWidget());
        dataGrid.setVisible(false);
    }

    /**
     * Удаляет текуший опросник (из отображения)
     *
     * @param event клик на кнопке
     */
    @SuppressWarnings("UnusedParameters")
    @UiHandler("clearInquirerButton")
    public void onRemoveButton(ClickEvent event) {
        if (Window.confirm("Вы уверены? Все несохранённые данные будут потеряны!")) {
            resetInquirerPanel();
//            presenter.deleteAllInquirers(); //удалит ВСЕ опросники из БД
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("saveInquirer")
    public void onSaveInquirer(ClickEvent event) {
//        presenter.addTestInquirer();
        presenter.addInquirer(createInquirer());
    }

    public void resetInquirerPanel() {
        presenter.initUpdateView();
        saveInquirer.setVisible(false);
        clearInquirerButton.setVisible(false);
        addInquirerButton.setEnabled(true);
        deleteInquirerButton.setVisible(true);
        editInquirerButton.setVisible(true);
        dataGrid.setVisible(true);
        inquirerPanel.clear();
    }

    /**
     * Создает новый опросник содержащий поля введённые в форму отображения
     *
     * @return свежеиспечённый опросник)
     */
    private InquirerDTO createInquirer() {
        CreateInquirerWidget inquirerWidget =
                (CreateInquirerWidget) inquirerPanel.getWidget(0);
        InquirerDTO inquirerDTO = new InquirerDTO(inquirerWidget.getInquirerName(),
                inquirerWidget.getInquirerDescription(), inquirerWidget.isPublished());

        for (Widget nextQuestion : inquirerWidget.getQuestionPanel()) {
            if (nextQuestion instanceof CreateQuestionWidget) {
                CreateQuestionWidget questionWidget = (CreateQuestionWidget) nextQuestion;
                QuestionDTO questionDTO =
                        new QuestionDTO(questionWidget.getQuestionDescription(),
                                questionWidget.getAnswerType());
                inquirerDTO.getQuestionsList().add(questionDTO);

                for (Widget nextAnswer :
                        ((CreateQuestionWidget) nextQuestion).getAnswerPanel()) {
                    if (nextAnswer instanceof CreateAnswerWidget) {
                        CreateAnswerWidget answerWidget = (CreateAnswerWidget) nextAnswer;
                        questionDTO.getAnswersList().add(
                                new AnswerDTO(answerWidget.getAnswerDescription(),
                                        answerWidget.isRightAnswer()));
                    }
                }
            }
        }

        return inquirerDTO;
    }

    @UiConstructor
    public CreatorView(CreatorPresenter presenter) {
        this.presenter = presenter;
        dataGrid = new InquirerDataGridWidget(presenter.getModel());
    }

    @Override
    public void init() {
        dataGrid.init();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {
        dataGrid.refresh();
    }
}