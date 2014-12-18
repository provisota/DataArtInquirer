package com.dataart.inquirer.client.view.creator;

import com.dataart.inquirer.client.presenter.CreatorPresenter;
import com.dataart.inquirer.client.view.IView;
import com.dataart.inquirer.client.view.inquirerDataGrid.InquirerDataGridWidget;
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

import java.util.List;

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
    @UiHandler("editInquirerButton")
    public void onEditInquirer(ClickEvent event) {
        InquirerDTO inquirerDTO = presenter.getModel().getSelectedInquirerDTO();
        if (inquirerDTO == null) {
            Window.alert("Сначала выберите существующий опросник из базы данных");
            return;
        }
        setAddEditButtonsGroup();
        showExistingInquirer(inquirerDTO);
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("deleteInquirerButton")
    public void onDeleteInquirer(ClickEvent event) {
        presenter.deleteSelectedInquirer();
        resetInquirerPanel();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("addInquirerButton")
    public void onAddInquirer(ClickEvent event) {
        dataGrid.resetSelection();
        presenter.getModel().setSelectedInquirerDTO(null);
        setAddEditButtonsGroup();
        inquirerPanel.add(new CreateInquirerWidget());
    }

    /**
     * Удаляет текуший опросник (из отображения)
     *
     * @param event клик на кнопке
     */
    @SuppressWarnings("UnusedParameters")
    @UiHandler("clearInquirerButton")
    public void onClearButton(ClickEvent event) {
        if (Window.confirm("Вы уверены? Все несохранённые данные будут потеряны!")) {
            dataGrid.resetSelection();
            resetInquirerPanel();
//            presenter.deleteAllInquirers(); //удалит ВСЕ опросники из БД
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("saveInquirer")
    public void onSaveInquirer(ClickEvent event) {
//        presenter.addTestInquirer();
        if (hasEmptyQuestionType()) {
            Window.alert("Укажите во ВСЕХ вопросах тип правильного ответа!");
            return;
        }
        presenter.addInquirer(createInquirer());
        dataGrid.resetSelection();
    }

    /**
     * Проверяет во всех ли вопросах указан тип правильного ответа.
     * @return true - если есть вопросы где не тип правильного ответа <br>
     * false - усли во всех вопросах указан тип правильного ответа
     */
    private boolean hasEmptyQuestionType() {
        VerticalPanel questionsPanel = ((CreateInquirerWidget) inquirerPanel
                .getWidget(0)).getQuestionPanel();
        for (Widget widget : questionsPanel){
            if (widget instanceof CreateQuestionWidget){
                if (((CreateQuestionWidget) widget).getAnswerType() == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Добавляет в отображение уже созданный опросник из БД для его редактирования
     *
     * @param inquirerDTO сущность опросника для редактирования
     */
    private void showExistingInquirer(InquirerDTO inquirerDTO) {
        CreateInquirerWidget inquirerWidget = new CreateInquirerWidget(
                inquirerDTO.getId(), inquirerDTO.getName(),
                inquirerDTO.getDescription(), inquirerDTO.isPublished());
        inquirerPanel.add(inquirerWidget);

        List<QuestionDTO> questionDTOs = inquirerDTO.getQuestionsList();
        if (questionDTOs != null) {
            for (QuestionDTO questionDTO : questionDTOs) {
                CreateQuestionWidget createQuestionWidget = new CreateQuestionWidget(
                        questionDTO.getId(), questionDTO.getDescription(),
                        questionDTO.getAnswerType());
                inquirerWidget.getQuestionPanel().add(createQuestionWidget);
                List<AnswerDTO> answerDTOs = questionDTO.getAnswersList();
                if (answerDTOs != null) {
                    for (AnswerDTO answerDTO : answerDTOs) {
                        createQuestionWidget.getAnswerPanel().add(new CreateAnswerWidget(
                                answerDTO.getId(), answerDTO.getDescription(),
                                answerDTO.isRightAnswer(),
                                createQuestionWidget.getAnswerType()));
                    }
                }
                createQuestionWidget.blockCheckBoxes();
            }
        }
    }

    private void setAddEditButtonsGroup() {
        saveInquirer.setVisible(true);
        clearInquirerButton.setVisible(true);
        addInquirerButton.setEnabled(false);
        deleteInquirerButton.setVisible(false);
        editInquirerButton.setVisible(false);
        dataGrid.setVisible(false);
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
        InquirerDTO inquirerDTO = new InquirerDTO(inquirerWidget.getId(),
                inquirerWidget.getInquirerName(), inquirerWidget.getInquirerDescription(),
                inquirerWidget.isPublished());

        for (Widget nextQuestion : inquirerWidget.getQuestionPanel()) {
            if (nextQuestion instanceof CreateQuestionWidget) {
                CreateQuestionWidget questionWidget = (CreateQuestionWidget) nextQuestion;
                QuestionDTO questionDTO =
                        new QuestionDTO(questionWidget.getId(),
                                questionWidget.getQuestionDescription(),
                                questionWidget.getAnswerType());
                inquirerDTO.getQuestionsList().add(questionDTO);

                for (Widget nextAnswer :
                        ((CreateQuestionWidget) nextQuestion).getAnswerPanel()) {
                    if (nextAnswer instanceof CreateAnswerWidget) {
                        CreateAnswerWidget answerWidget = (CreateAnswerWidget) nextAnswer;
                        questionDTO.getAnswersList().add(
                                new AnswerDTO(answerWidget.getId(),
                                        answerWidget.getAnswerDescription(),
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