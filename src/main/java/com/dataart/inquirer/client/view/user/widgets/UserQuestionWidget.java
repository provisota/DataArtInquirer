package com.dataart.inquirer.client.view.user.widgets;

import com.dataart.inquirer.shared.dto.AnswerDTO;
import com.dataart.inquirer.shared.dto.QuestionDTO;
import com.dataart.inquirer.shared.enums.AnswerType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.TextBox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<String> selectedAnswersSet = new HashSet<>();

    public UserQuestionWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public UserQuestionWidget(int questionNumber, QuestionDTO questionDTO) {
        this();
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

    private void addTextBoxAnswer() {
        answerPanel.addStyleName("user-answer-panel");

        final TextBox textBox = new TextBox();
        textBox.setPlaceholder("введите сюда ваш ответ");
        textBox.setWidth("50%");
        textBox.addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                selectedAnswersSet.clear();
                selectedAnswersSet.add(textBox.getText());
            }
        });
        answerPanel.add(textBox);
    }

    private void addCheckBoxAnswers(List<AnswerDTO> answersList) {
        answerPanel.addStyleName("user-answer-panel-with-line");
        for (AnswerDTO answerDTO : answersList) {
            final CheckBox checkBox = new CheckBox(answerDTO.getDescription());
            checkBox.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (checkBox.getValue()) {
                        selectedAnswersSet.add(checkBox.getText());
                    } else {
                        selectedAnswersSet.remove(checkBox.getText());
                    }
                }
            });
            answerPanel.add(checkBox);
        }
    }

    private void addSelectAnswers(List<AnswerDTO> answersList) {
        answerPanel.addStyleName("user-answer-panel");

        final ListBox listBox = new ListBox();
        listBox.setWidth("30%");
        listBox.addItem("");
        listBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                selectedAnswersSet.clear();
                selectedAnswersSet.add(listBox.getSelectedItemText());
            }
        });
        for (AnswerDTO answerDTO : answersList) {
            listBox.addItem(answerDTO.getDescription());
        }
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
                    selectedAnswersSet.clear();
                    selectedAnswersSet.add(radioButton.getText());
                }
            });
            answerPanel.add(radioButton);
        }
    }
}