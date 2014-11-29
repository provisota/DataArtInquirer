package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.resources.ImageResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Alterovych Ilya
 */
public class AccessDeniedView extends Composite{

    interface accessDeniedViewUiBinder extends UiBinder<VerticalPanel, AccessDeniedView> {}
    private static accessDeniedViewUiBinder ourUiBinder = GWT.create(accessDeniedViewUiBinder.class);
    private static AccessDeniedView instance;

    @UiField
    ImageResources res;

    @UiConstructor
    private AccessDeniedView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public static AccessDeniedView getInstance(){
        if (instance == null){
            instance = new AccessDeniedView();
        }
        return instance;
    }
}