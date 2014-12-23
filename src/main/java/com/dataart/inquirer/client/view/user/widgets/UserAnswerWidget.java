package com.dataart.inquirer.client.view.user.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Alterovych Ilya
 */
public class UserAnswerWidget extends Composite {
    interface UserAnswerWidgetUiBinder extends UiBinder<VerticalPanel, UserAnswerWidget> {
    }

    private static UserAnswerWidgetUiBinder ourUiBinder = GWT.create(UserAnswerWidgetUiBinder.class);

    public UserAnswerWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}