package com.dataart.inquirer.client.view.creator.widgets;

import com.dataart.inquirer.shared.dto.inquirer.QuestionDTO;
import com.dataart.inquirer.shared.enums.AnswerType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public class CreateQuestionWidget extends Composite {

    interface CreateQuestionViewUiBinder extends UiBinder<VerticalPanel,
            CreateQuestionWidget> {
    }

    private static CreateQuestionViewUiBinder ourUiBinder =
            GWT.create(CreateQuestionViewUiBinder.class);

    public CreateQuestionWidget(QuestionDTO questionDTO) {
        this();
        this.id = questionDTO.getId();
        this.questionDescription.setText(questionDTO.getDescription());
        setAnswerType(questionDTO.getAnswerType());
        addAnswerButton.setEnabled(true);
    }

    @UiConstructor
    public CreateQuestionWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    private AnswerType answerType;
    private int id;
    private Set<Widget> allParentWidgets = new HashSet<>();

    @UiField
    TextBox questionDescription;
    @UiField
    ListBox answerTypeListBox;
    @UiField
    Button addAnswerButton;
    @UiField
    Button removeQuestion;
    @UiField
    VerticalPanel answerPanel;
    @UiField
    Tooltip answerTypeTooltip;
    @UiField
    InputGroupAddon questionNumber;

    @SuppressWarnings("UnusedParameters")
    @UiHandler("questionDescription")
    public void onQuestionDescriptionFocused(FocusEvent event) {
        questionDescription.removeStyleName("error-text-field");
    }

    /**
     * Удаляет this вопрос и перенумеровывает оставшиеся вопросы
     * @param event клик по кнопке
     */
    @SuppressWarnings("UnusedParameters")
    @UiHandler("removeQuestion")
    public void onRemoveButton(ClickEvent event) {
        allParentWidgets.clear();
        getAllParents(this);
        this.removeFromParent();

        for (Widget parent : allParentWidgets){
            if (parent instanceof CreateInquirerWidget){
                ((CreateInquirerWidget) parent).setQuestionNumbers();
            }
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("addAnswerButton")
    public void onAddAnswer(ClickEvent event) {
        CreateAnswerWidget createAnswerWidget = new CreateAnswerWidget(answerType);
        for (Widget widget : answerPanel) {
            if (((CreateAnswerWidget) widget).hasOneAnswer()) {
                createAnswerWidget.getIsRightAnswerBox().setEnabled(false);
            }
        }
        answerPanel.add(createAnswerWidget);
        setAnswerNumbers();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("answerTypeListBox")
    public void onAnswerTypeChange(ChangeEvent changeEvent) {
        addAnswerButton.setEnabled(true);
        int selectedIndex = answerTypeListBox.getSelectedIndex();
        String answerType = answerTypeListBox.getValue(selectedIndex);
        answerTypeTooltip.hide();
        if ("RadioButton".equals(answerType)) {
            this.answerType = AnswerType.RADIO_BUTTON;
            answerTypeTooltip.setTitle("один вариант ответа");
        } else if ("Select".equals(answerType)) {
            this.answerType = AnswerType.SELECT;
            answerTypeTooltip.setTitle("один вариант ответа");
        } else if ("CheckBox".equals(answerType)) {
            this.answerType = AnswerType.CHECK_BOX;
            answerTypeTooltip.setTitle("несколько вариантов ответа");
        } else if ("TextBox".equals(answerType)) {
            this.answerType = AnswerType.TEXT_BOX;
            answerTypeTooltip.setTitle("текстовое поле для ответа");
            answerPanel.clear();
            addAnswerButton.setEnabled(false);
        } else {
            this.answerType = null;
            addAnswerButton.setEnabled(false);
            answerTypeTooltip.setTitle("выберите тип ответа");
        }
        answerTypeTooltip.show();
        answerTypeTooltip.reconfigure();
        changeWidgetsAnswerType();
    }

    public void setAnswerNumbers() {
        int number = 1;
        for (Widget widget : answerPanel) {
            if (widget instanceof CreateAnswerWidget) {
                CreateAnswerWidget answerWidget = (CreateAnswerWidget) widget;
                answerWidget.setAnswerNumber(number);
            }
            ++number;
        }
    }

    private void getAllParents(Widget widget) {
        Widget parent = widget.getParent();
        if (parent != null){
            allParentWidgets.add(parent);
            getAllParents(parent);
        }
    }

    public void setQuestionNumber(int number) {
        questionNumber.setText("Вопрос №" + number);
    }

    /**
     * Изменяет AnswerType во всех уже добавленых ответах
     */
    private void changeWidgetsAnswerType() {
        for (Widget widget : answerPanel) {
            if (widget instanceof CreateAnswerWidget) {
                CreateAnswerWidget answerWidget = (CreateAnswerWidget) widget;
                answerWidget.setAnswerType(this.answerType);
                answerWidget.getIsRightAnswerBox().setEnabled(true);
                answerWidget.getIsRightAnswerBox().setValue(false);
                answerWidget.setHasOneAnswer(false);
            }
        }
    }

    public void blockCheckBoxes() {
        if (answerType == AnswerType.TEXT_BOX){
            addAnswerButton.setEnabled(false);
            return;
        }
        for (Widget widget : answerPanel) {
            if (widget instanceof CreateAnswerWidget) {
                CreateAnswerWidget answerWidget = (CreateAnswerWidget) widget;
                if (answerWidget.getAnswerType() == AnswerType.RADIO_BUTTON
                        || answerWidget.getAnswerType() == AnswerType.SELECT) {
                    answerWidget.setHasOneAnswer(true);
                    if (!answerWidget.isRightAnswerBox.getValue()) {
                        answerWidget.isRightAnswerBox.setEnabled(false);
                    }
                }
            }
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public AnswerType getAnswerType() {
        return answerType;
    }

    public VerticalPanel getAnswerPanel() {
        return answerPanel;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
        answerTypeListBox.setSelectedIndex(answerType.ordinal() + 1);
    }

    public String getQuestionDescription() {
        return questionDescription.getValue();
    }

    public Integer getId() {
        return id;
    }

    public TextBox getDescriptionTextBox() {
        return questionDescription;
    }
}