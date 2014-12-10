package com.dataart.inquirer.client.view;

import com.dataart.inquirer.client.presenter.AdminPresenter;
import com.dataart.inquirer.client.resources.ImageResources;
import com.dataart.inquirer.shared.dto.UserDTO;
import com.dataart.inquirer.shared.enums.Role;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public class AdminView extends Composite implements IView{

    interface AdminViewUiBinder extends UiBinder<VerticalPanel, AdminView> {}
    private static AdminViewUiBinder ourUiBinder = GWT.create(AdminViewUiBinder.class);
    private final AdminPresenter presenter;
    private MultiSelectionModel<UserDTO> selectionModel;
    private ArrayList<UserDTO> userList;
    @UiField(provided = true)
    DataGrid<UserDTO> dataGrid;
    @UiField
    FlowPanel roleButtonsPanel;
    @UiField
    Button setAdminButton;
    @UiField
    Button setUserButton;

    @UiConstructor
    public AdminView(AdminPresenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("setAdminButton")
    public void onSetAdminClick(ClickEvent event){
        Set<UserDTO> selectedUsers = selectionModel.getSelectedSet();
        for(UserDTO userDTO : selectedUsers){
            userDTO.setRole(Role.ROLE_ADMIN);
        }
        presenter.updateUserRoles(selectedUsers);
        selectionModel.clear();
    }

    @UiHandler("setUserButton")
    public void onSetUserClick(ClickEvent event){
        Set<UserDTO> selectedUsers = selectionModel.getSelectedSet();
        for(UserDTO userDTO : selectedUsers){
            userDTO.setRole(Role.ROLE_USER);
        }
        presenter.updateUserRoles(selectedUsers);
        selectionModel.clear();
    }

    private void setupDataGrid() {
        dataGrid = new DataGrid<>(100);
        dataGrid.setVisible(false);
        dataGrid.setEmptyTableWidget(new Image(ImageResources.resources.noData()));
        setSelectionModel();
        initDataGridColumns();
    }

    private void setSelectionModel() {
        // Add a selection model so we can select cells.
        selectionModel = new MultiSelectionModel<>(
                new ProvidesKey<UserDTO>() {
            @Override
            public Object getKey(UserDTO item) {
                return item;
            }
        });
        dataGrid.setSelectionModel(selectionModel,
                DefaultSelectionEventManager.<UserDTO>createCheckboxManager());
    }

    private void initDataGridColumns() {
        new UserColumn(dataGrid, "Id", 24) {
            @Override
            public String getValue(UserDTO userDTO) {
                return String.valueOf(userDTO.getId());
            }
        };

        new UserColumn(dataGrid, "Логин", 24) {
            @Override
            public String getValue(UserDTO userDTO) {
                return userDTO.getUsername();
            }
        };

        new UserColumn(dataGrid, "E-mail", 24) {
            @Override
            public String getValue(UserDTO userDTO) {
                return userDTO.getEmail();
            }
        };

        new UserColumn(dataGrid, "Права доступа", 24) {
            @Override
            public String getValue(UserDTO userDTO) {
                return userDTO.getRole().name();
            }
        };

        //add CheckBox column to DataGrid
        Column<UserDTO, Boolean> checkBoxColumn =
                new Column<UserDTO, Boolean>(new CheckboxCell(true, false)){
            @Override
            public Boolean getValue(UserDTO userDTO) {
                return selectionModel.isSelected(userDTO);
            }
        };
//        checkBoxColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        dataGrid.addColumn(checkBoxColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        dataGrid.setColumnWidth(checkBoxColumn, 6, Style.Unit.PCT);
    }

    @Override
    public void init() {
        setupDataGrid();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {
        userList = presenter.getModel().getUserDTOs();
        dataGrid.setRowCount(userList.size(), true);
        dataGrid.setRowData(0, userList);
        dataGrid.redraw();
//        Window.alert(String.valueOf(presenter.getModel().getUserDTOs()));
    }
}