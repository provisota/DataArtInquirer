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
public class CreateAnswerWidget extends Composite {
    interface CreateAnswerViewUiBinder extends UiBinder<VerticalPanel, CreateAnswerWidget> {
    }

    private static CreateAnswerViewUiBinder ourUiBinder =
            GWT.create(CreateAnswerViewUiBinder.class);

    @UiConstructor
    public CreateAnswerWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiField
    TextBox answerDescription;
    @UiField
    CheckBox isRightAnswerBox;
    @UiField
    Button removeAnswer;

    @SuppressWarnings("UnusedParameters")
    @UiHandler("removeAnswer")
    public void onRemoveButton(ClickEvent event){
        this.removeFromParent();
    }

    public String getAnswerDescription() {
        return answerDescription.getValue();
    }

    public void setAnswerDescription(String answerDescription) {
        this.answerDescription.setValue(answerDescription);
    }

    public boolean isRightAnswer() {
        return isRightAnswerBox.getValue();
    }

    public void setIsRightAnswer(boolean isRightAnswerBox) {
        this.isRightAnswerBox.setValue(isRightAnswerBox);
    }
}
