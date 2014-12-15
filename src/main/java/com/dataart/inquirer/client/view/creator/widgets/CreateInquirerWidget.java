package com.dataart.inquirer.client.view.creator.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.TextBox;

/**
 * @author Alterovych Ilya
 */
public class CreateInquirerWidget extends Composite {
    interface CreateInquirerViewUiBinder extends UiBinder<VerticalPanel, CreateInquirerWidget> {
    }

    private static CreateInquirerViewUiBinder ourUiBinder =
            GWT.create(CreateInquirerViewUiBinder.class);

    public CreateInquirerWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    @UiField
    VerticalPanel questionPanel;
    @UiField
    TextBox inquirerName;
    @UiField
    TextBox inquirerDescription;
    @UiField
    CheckBox isPublished;
    @UiField
    Button addAnswerButton;

    @SuppressWarnings("UnusedParameters")
    @UiHandler("addAnswerButton")
    public void onAddAnswer(ClickEvent event) {
        questionPanel.add(new CreateQuestionWidget());
    }

    public String getInquirerName() {
        return inquirerName.getValue();
    }

    public void setInquirerName(String inquirerName) {
        this.inquirerName.setText(inquirerName);
    }

    public String getInquirerDescription() {
        return inquirerDescription.getValue();
    }

    public void setInquirerDescription(String inquirerDescription) {
        this.inquirerDescription.setText(inquirerDescription);
    }

    public boolean isPublished() {
        return isPublished.getValue();
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished.setValue(isPublished);
    }

    public VerticalPanel getQuestionPanel() {
        return questionPanel;
    }
}