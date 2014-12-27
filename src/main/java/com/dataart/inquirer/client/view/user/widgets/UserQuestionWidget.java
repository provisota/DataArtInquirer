package com.dataart.inquirer.client.view.user.widgets;

import com.dataart.inquirer.shared.dto.inquirer.AnswerDTO;
import com.dataart.inquirer.shared.dto.inquirer.QuestionDTO;
import com.dataart.inquirer.shared.enums.AnswerType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.TextBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alterovych Ilya
 */
public class UserQuestionWidget extends Composite {

    interface UserQuestionWidgetUiBinder extends UiBinder<VerticalPanel, UserQuestionWidget> {
    }

    private static UserQuestionWidgetUiBinder ourUiBinder = GWT.create(UserQuestionWidgetUiBinder.class);
    @UiField
    Label questionDescription;
    @UiField
    VerticalPanel answerPanel;
    private Map<String, Boolean> answersMap = new HashMap<>();
    private QuestionDTO questionDTO;

    public UserQuestionWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public UserQuestionWidget(int questionNumber, QuestionDTO questionDTO) {
        this();
        this.questionDTO = questionDTO;
        if (questionDTO.getAnswerType() != AnswerType.TEXT_BOX) {
            resetAnswersMap();
        }
        this.questionDescription.setText(questionNumber + ". " +
                questionDTO.getDescription());
        AnswerType answerType = questionDTO.getAnswerType();
        if (answerType == AnswerType.RADIO_BUTTON) {
            addRadioAnswers(questionDTO.getAnswersList());
        }
        if (answerType == AnswerType.SELECT) {
            addSelectAnswers(questionDTO.getAnswersList());
        }
        if (answerType == AnswerType.CHECK_BOX) {
            addCheckBoxAnswers(questionDTO.getAnswersList());
        }
        if (answerType == AnswerType.TEXT_BOX) {
            addTextBoxAnswer();
        }
    }

    private void resetAnswersMap() {
        for (AnswerDTO answerDTO : questionDTO.getAnswersList()) {
            answersMap.put(answerDTO.getDescription(), false);
        }
    }

    private void addTextBoxAnswer() {
        answerPanel.addStyleName("user-answer-panel");

        final TextBox textBox = new TextBox();
        textBox.setPlaceholder("введите сюда ваш ответ");
        textBox.setWidth("50%");
        textBox.addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                answersMap.clear();
                if (!"".equals(textBox.getText())) {
                    answersMap.put(textBox.getText(), true);
                }
            }
        });
        addFocusHandler(textBox);
        answerPanel.add(textBox);
    }

    private void addCheckBoxAnswers(List<AnswerDTO> answersList) {
        answerPanel.addStyleName("user-answer-panel-with-line");
        for (AnswerDTO answerDTO : answersList) {
            final CheckBox checkBox = new CheckBox(answerDTO.getDescription());
            checkBox.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    answersMap.put(checkBox.getText(), checkBox.getValue());
                }
            });
            addFocusHandler(checkBox);
            answerPanel.add(checkBox);
        }
    }

    private void addSelectAnswers(List<AnswerDTO> answersList) {
        answerPanel.addStyleName("user-answer-panel");

        final ListBox listBox = new ListBox();
        listBox.addItem("");
        listBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                resetAnswersMap();
                if (!"".equals(listBox.getSelectedItemText())) {
                    answersMap.put(listBox.getSelectedItemText(), true);
                }
            }
        });
        for (AnswerDTO answerDTO : answersList) {
            listBox.addItem(answerDTO.getDescription());
        }
        addFocusHandler(listBox);
        answerPanel.add(listBox);
    }

    private void addRadioAnswers(List<AnswerDTO> answersList) {
        answerPanel.addStyleName("user-answer-panel-with-line");

        for (AnswerDTO answerDTO : answersList) {
            final RadioButton radioButton = new RadioButton("radioGroup",
                    answerDTO.getDescription());
            radioButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    resetAnswersMap();
                    answersMap.put(radioButton.getText(), true);
                }
            });
            addFocusHandler(radioButton);
            answerPanel.add(radioButton);
        }
    }

    private void addFocusHandler(FocusWidget widget) {
        widget.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                answerPanel.removeStyleName("error-text-field");
            }
        });
    }

    public Map<String, Boolean> getAnswersMap() {
        return answersMap;
    }

    public VerticalPanel getAnswerPanel() {
        return answerPanel;
    }
}