package com.dataart.inquirer.client.view.creator.widgets;

import com.dataart.inquirer.shared.dto.AnswerDTO;
import com.dataart.inquirer.shared.enums.AnswerType;
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
import org.gwtbootstrap3.client.ui.InputGroupAddon;
import org.gwtbootstrap3.client.ui.TextBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public class CreateAnswerWidget extends Composite {
    interface CreateAnswerViewUiBinder extends UiBinder<VerticalPanel,
            CreateAnswerWidget> {
    }

    private static CreateAnswerViewUiBinder ourUiBinder =
            GWT.create(CreateAnswerViewUiBinder.class);

    public CreateAnswerWidget(AnswerDTO answerDTO, AnswerType answerType) {
        this();
        this.id = answerDTO.getId();
        this.answerDescription.setText(answerDTO.getDescription());
        this.isRightAnswerBox.setValue(answerDTO.isRightAnswer());
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
    private Set<Widget> allParentWidgets = new HashSet<>();

    @UiField
    TextBox answerDescription;
    @UiField
    CheckBox isRightAnswerBox;
    @UiField
    Button removeAnswer;
    @UiField
    InputGroupAddon answerNumber;

    @SuppressWarnings("UnusedParameters")
    @UiHandler("answerDescription")
    public void onAnswerDescriptionFocused(FocusEvent event){
        answerDescription.removeStyleName("error-text-field");
    }

    /**
     * Удаляет this ответ и перенумеровывает оставшиеся ответы
     * @param event клик по кнопке
     */
    @SuppressWarnings("UnusedParameters")
    @UiHandler("removeAnswer")
    public void onRemoveButton(ClickEvent event) {
        allParentWidgets.clear();
        getAllParents(this);
        this.removeFromParent();

        for (Widget parent : allParentWidgets){
            if (parent instanceof CreateQuestionWidget){
                ((CreateQuestionWidget) parent).setAnswerNumbers();
            }
        }
    }

    private void getAllParents(Widget widget) {
        Widget parent = widget.getParent();
        if (parent != null){
            allParentWidgets.add(parent);
            getAllParents(parent);
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("isRightAnswerBox")
    public void onRightAnswerSelect(ClickEvent event) {
        blockCheckBoxesIfNeeded();
        getParent().removeStyleName("error-text-field");
    }

    public void setAnswerNumber(int number) {
        answerNumber.setText("Вариант ответа №" + number);
    }

    private void blockCheckBoxesIfNeeded() {
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

    public AnswerType getAnswerType() {
        return answerType;
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

    public TextBox getDescriptionTextBox(){
        return answerDescription;
    }
}
