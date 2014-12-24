package com.dataart.inquirer.client.view.user.widgets;

import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.inquirer.QuestionDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.gwtbootstrap3.client.ui.Legend;

/**
 * @author Alterovych Ilya
 */
public class UserInquirerWidget extends Composite {
    interface UserInquirerWidgetUiBinder extends UiBinder<VerticalPanel, UserInquirerWidget> {
    }

    private static UserInquirerWidgetUiBinder ourUiBinder = GWT.create(UserInquirerWidgetUiBinder.class);
    @UiField
    Label inquirerDescription;
    @UiField
    VerticalPanel questionPanel;
    @UiField
    Legend inquirerName;

    @UiConstructor
    public UserInquirerWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public UserInquirerWidget(InquirerDTO inquirerDTO) {
        this();
        this.inquirerName.setText(inquirerDTO.getName());
        this.inquirerDescription.setText(inquirerDTO.getDescription());
        int questionNumber = 1;
        for (QuestionDTO questionDTO : inquirerDTO.getQuestionsList()){
            questionPanel.add(new UserQuestionWidget(questionNumber, questionDTO));
            questionNumber++;
        }
    }

    public VerticalPanel getQuestionPanel() {
        return questionPanel;
    }
}