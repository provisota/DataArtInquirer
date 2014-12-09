package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.presenter.CreatorPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.gwtbootstrap3.client.ui.Button;

/**
 * @author Alterovych Ilya
 */
public class CreatorView extends Composite implements IView {

    interface creatorViewUiBinder extends UiBinder<VerticalPanel, CreatorView> {}
    private static creatorViewUiBinder ourUiBinder = GWT.create(creatorViewUiBinder.class);
    private final CreatorPresenter presenter;
    @UiField
    Button addInquirerButton;

    @UiConstructor
    public CreatorView(CreatorPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {

    }
}