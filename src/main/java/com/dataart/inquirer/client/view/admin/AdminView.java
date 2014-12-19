package com.dataart.inquirer.client.view.admin;

import com.dataart.inquirer.client.presenter.AdminPresenter;
import com.dataart.inquirer.client.resources.ImageResources;
import com.dataart.inquirer.client.view.IView;
import com.dataart.inquirer.client.view.admin.columns.ColumnsHolder;
import com.dataart.inquirer.client.view.admin.comparators.ComparatorsHolder;
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
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public class AdminView extends Composite implements IView {

    interface AdminViewUiBinder extends UiBinder<VerticalPanel, AdminView> {
    }

    private static AdminViewUiBinder ourUiBinder = GWT.create(AdminViewUiBinder.class);
    private final AdminPresenter presenter;
    private MultiSelectionModel<UserDTO> selectionModel;
    private ArrayList<UserDTO> userList;
    private ColumnSortEvent.ListHandler<UserDTO> sortHandler;
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

    @SuppressWarnings("UnusedParameters")
    @UiHandler("setAdminButton")
    public void onSetAdminClick(ClickEvent event) {
        changeUserRole(Role.ROLE_ADMIN);
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("setUserButton")
    public void onSetUserClick(ClickEvent event) {
        changeUserRole(Role.ROLE_USER);
    }

    private void changeUserRole(Role role) {
        Set<UserDTO> selectedUsers = new HashSet<>();
        for (UserDTO userDTO : selectionModel.getSelectedSet()) {
            selectedUsers.add(userDTO.cloneUserDTO());
        }
        for (UserDTO userDTO : selectedUsers) {
            userDTO.setRole(role);
        }
        presenter.updateUserRoles(selectedUsers);
        selectionModel.clear();
    }

    private void setupDataGrid() {
        dataGrid = new DataGrid<>(100);
        dataGrid.setVisible(true);
        dataGrid.setEmptyTableWidget(new Image(ImageResources.resources.noData()));
        addColumnSortHandler();
        setSelectionModel();
        initDataGridColumns();
    }

    /**
     * add Sort Handler to Columns so we can sort it.
     */
    private void addColumnSortHandler() {
        sortHandler = new ColumnSortEvent.ListHandler<UserDTO>(presenter.getModel()
                .getUserDTOs()) {
            @Override
            public void onColumnSort(ColumnSortEvent event) {
                setList(presenter.getModel().getUserDTOs());
                super.onColumnSort(event);
                presenter.getModel().setUserDTOs(
                        (ArrayList<UserDTO>) getList());
                refresh();
            }
        };
        dataGrid.addColumnSortHandler(sortHandler);
    }

    /**
     * Add a selection model so we can select cells.
     */
    private void setSelectionModel() {
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
        ColumnsHolder columnsHolder = new ColumnsHolder(dataGrid);
        ComparatorsHolder comparatorsHolder = new ComparatorsHolder();

        sortHandler.setComparator(columnsHolder.getIdColumn(),
                comparatorsHolder.getIdComparator());
        sortHandler.setComparator(columnsHolder.getUsernameColumn(),
                comparatorsHolder.getUsernameComparator());
        sortHandler.setComparator(columnsHolder.getEmailColumn(),
                comparatorsHolder.getEmailComparator());
        sortHandler.setComparator(columnsHolder.getRoleColumn(),
                comparatorsHolder.getRoleComparator());

        //add CheckBox column to DataGrid
        Column<UserDTO, Boolean> checkBoxColumn =
                new Column<UserDTO, Boolean>(new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(UserDTO userDTO) {
                        return selectionModel.isSelected(userDTO);
                    }
                };
        checkBoxColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
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