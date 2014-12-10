package com.dataart.inquirer.client.view.creator;

import com.dataart.inquirer.client.presenter.CreatorPresenter;
import com.dataart.inquirer.client.view.IView;
import com.dataart.inquirer.client.view.creator.widgets.CreateInquirerWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
    @UiField
    Button saveInquirer;
    @UiField
    VerticalPanel inquirerPanel;
    @UiField
    Button removeInquirer;

    @UiHandler("addInquirerButton")
    public void onAddInquirer(ClickEvent event){
        saveInquirer.setVisible(true);
        removeInquirer.setVisible(true);
        addInquirerButton.setEnabled(false);
        inquirerPanel.add(new CreateInquirerWidget());
    }

    @UiHandler("removeInquirer")
    public void onRemoveButton(ClickEvent event){
        saveInquirer.setVisible(false);
        removeInquirer.setVisible(false);
        addInquirerButton.setEnabled(true);
        inquirerPanel.clear();
    }

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