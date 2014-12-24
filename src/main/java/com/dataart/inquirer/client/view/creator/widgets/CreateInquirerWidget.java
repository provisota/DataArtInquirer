package com.dataart.inquirer.client.view.creator.widgets;

import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.TextBox;

/**
 * @author Alterovych Ilya
 */
public class CreateInquirerWidget extends Composite {
    interface CreateInquirerViewUiBinder extends UiBinder<VerticalPanel,
            CreateInquirerWidget> {
    }

    private static CreateInquirerViewUiBinder ourUiBinder =
            GWT.create(CreateInquirerViewUiBinder.class);

    public CreateInquirerWidget(InquirerDTO inquirerDTO) {
        this();
        this.id = inquirerDTO.getId();
        this.inquirerName.setText(inquirerDTO.getName());
        this.inquirerDescription.setText(inquirerDTO.getDescription());
        this.isPublished.setValue(inquirerDTO.isPublished());
    }

    @UiConstructor
    public CreateInquirerWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    private int id;

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
        setQuestionNumbers();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("inquirerName")
    public void onInquirerNameFocused(FocusEvent event) {
        inquirerName.removeStyleName("error-text-field");
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("inquirerDescription")
    public void onInquirerDescriptionFocused(FocusEvent event) {
        inquirerDescription.removeStyleName("error-text-field");
    }

    public void setQuestionNumbers() {
        int number = 1;
        for (Widget widget : questionPanel) {
            if (widget instanceof CreateQuestionWidget) {
                CreateQuestionWidget questionWidget = (CreateQuestionWidget) widget;
                questionWidget.setQuestionNumber(number);
            }
            ++number;
        }
    }

    public String getInquirerName() {
        return inquirerName.getValue();
    }

    public String getInquirerDescription() {
        return inquirerDescription.getValue();
    }

    public boolean isPublished() {
        return isPublished.getValue();
    }

    public VerticalPanel getQuestionPanel() {
        return questionPanel;
    }

    public Integer getId() {
        return id;
    }

    public TextBox getNameTextBox() {
        return inquirerName;
    }

    public TextBox getDescriptionTextBox() {
        return inquirerDescription;
    }
}