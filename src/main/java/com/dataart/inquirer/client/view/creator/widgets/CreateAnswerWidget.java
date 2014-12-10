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

    @UiHandler("removeAnswer")
    public void onRemoveButton(ClickEvent event){
        this.removeFromParent();
    }
}
