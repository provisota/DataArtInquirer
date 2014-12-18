package com.dataart.inquirer.client.view.creator.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
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
    interface CreateInquirerViewUiBinder extends UiBinder<VerticalPanel,
            CreateInquirerWidget> {
    }

    private static CreateInquirerViewUiBinder ourUiBinder =
            GWT.create(CreateInquirerViewUiBinder.class);

    public CreateInquirerWidget(Integer id, String inquirerName, String inquirerDescription,
                                boolean isPublished) {
        this();
        this.id = id;
        this.inquirerName.setText(inquirerName);
        this.inquirerDescription.setText(inquirerDescription);
        this.isPublished.setValue(isPublished);
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
}