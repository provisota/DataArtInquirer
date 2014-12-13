package com.dataart.inquirer.client.view.creator.widgets;

import com.dataart.inquirer.shared.enums.AnswerType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.Tooltip;

/**
 * @author Alterovych Ilya
 */
public class CreateQuestionWidget extends Composite {
    interface CreateQuestionViewUiBinder extends UiBinder<VerticalPanel, CreateQuestionWidget> {
    }

    private static CreateQuestionViewUiBinder ourUiBinder = GWT.create(CreateQuestionViewUiBinder.class);

    public CreateQuestionWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    private AnswerType answerType;
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

    @SuppressWarnings("UnusedParameters")
    @UiHandler("removeQuestion")
    public void onRemoveButton(ClickEvent event) {
        this.removeFromParent();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("addAnswerButton")
    public void onAddAnswer(ClickEvent event) {
        answerPanel.add(new CreateAnswerWidget());
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("answerTypeListBox")
    public void onAnswerTypeChange(ChangeEvent changeEvent) {
        int selectedIndex = answerTypeListBox.getSelectedIndex();
        String answerType = answerTypeListBox.getValue(selectedIndex);
        answerTypeTooltip.hide();
        if ("CheckBox".equals(answerType)) {
            this.answerType = AnswerType.CHECK_BOX;
            answerTypeTooltip.setTitle("несколько вариантов ответа");
        } else if ("RadioButton".equals(answerType)) {
            this.answerType = AnswerType.RADIO_BUTTON;
            answerTypeTooltip.setTitle("один вариант ответа");
        } else if ("Select".equals(answerType)) {
            this.answerType = AnswerType.SELECT;
            answerTypeTooltip.setTitle("один вариант ответа");
        } else if ("TextBox".equals(answerType)) {
            this.answerType = AnswerType.TEXT_BOX;
            answerTypeTooltip.setTitle("текстовое поле для ответа");
        } else {
            this.answerType = null;
            answerTypeTooltip.setTitle("выберите тип ответа");
        }
        answerTypeTooltip.show();
        answerTypeTooltip.reconfigure();
    }

    @SuppressWarnings("UnusedDeclaration")
    public AnswerType getAnswerType() {
        return answerType;
    }
}