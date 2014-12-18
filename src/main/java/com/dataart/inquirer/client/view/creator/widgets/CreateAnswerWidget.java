package com.dataart.inquirer.client.view.creator.widgets;

import com.dataart.inquirer.shared.enums.AnswerType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
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

import java.util.ArrayList;

/**
 * @author Alterovych Ilya
 */
public class CreateAnswerWidget extends Composite {
    interface CreateAnswerViewUiBinder extends UiBinder<VerticalPanel,
            CreateAnswerWidget> {
    }

    private static CreateAnswerViewUiBinder ourUiBinder =
            GWT.create(CreateAnswerViewUiBinder.class);

    public CreateAnswerWidget(Integer id, String answerDescription,
                              boolean isRightAnswer, AnswerType answerType) {
        this();
        this.id = id;
        this.answerDescription.setText(answerDescription);
        this.isRightAnswerBox.setValue(isRightAnswer);
        this.answerType = answerType;
    }

    public CreateAnswerWidget(AnswerType answerType) {
        this();
        this.answerType = answerType;
    }

    @UiConstructor
    public CreateAnswerWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    private int id;
    private AnswerType answerType;
    private boolean hasOneAnswer;

    @UiField
    TextBox answerDescription;
    @UiField
    CheckBox isRightAnswerBox;
    @UiField
    Button removeAnswer;

    @SuppressWarnings("UnusedParameters")
    @UiHandler("removeAnswer")
    public void onRemoveButton(ClickEvent event) {
        this.removeFromParent();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("isRightAnswerBox")
    public void onRightAnswerSelect(ClickEvent event) {
        if (isRightAnswerBox.getValue()) {
            if (answerType == AnswerType.SELECT || answerType == AnswerType.RADIO_BUTTON) {
                hasOneAnswer = true;
                for (CreateAnswerWidget answerWidget : getAllAnswerWidgets()) {
                    if (!answerWidget.equals(this)) {
                        answerWidget.getIsRightAnswerBox().setEnabled(false);
                    }
                }
            }
        } else if ((answerType == AnswerType.SELECT ||
                answerType == AnswerType.RADIO_BUTTON) && hasOneAnswer) {
            hasOneAnswer = false;
            for (CreateAnswerWidget answerWidget : getAllAnswerWidgets()) {
                answerWidget.getIsRightAnswerBox().setEnabled(true);
            }
        }
    }

    private ArrayList<CreateAnswerWidget> getAllAnswerWidgets() {
        ArrayList<CreateAnswerWidget> answerWidgets = new ArrayList<>();
        for (Widget widget : ((VerticalPanel) getParent())) {
            if (widget instanceof CreateAnswerWidget) {
                answerWidgets.add((CreateAnswerWidget) widget);
            }
        }
        return answerWidgets;
    }

    public String getAnswerDescription() {
        return answerDescription.getValue();
    }

    public boolean isRightAnswer() {
        return isRightAnswerBox.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public CheckBox getIsRightAnswerBox() {
        return isRightAnswerBox;
    }

    public boolean hasOneAnswer() {
        return hasOneAnswer;
    }

    public void setHasOneAnswer(boolean hasOneAnswer) {
        this.hasOneAnswer = hasOneAnswer;
    }
}
