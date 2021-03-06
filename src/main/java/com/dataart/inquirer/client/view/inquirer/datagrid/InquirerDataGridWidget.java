package com.dataart.inquirer.client.view.inquirer.datagrid;

import com.dataart.inquirer.client.models.InquirerModel;
import com.dataart.inquirer.client.resources.ImageResources;
import com.dataart.inquirer.client.view.IView;
import com.dataart.inquirer.client.view.inquirer.datagrid.columns.ColumnsHolder;
import com.dataart.inquirer.client.view.inquirer.datagrid.comparators.ComparatorsHolder;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.*;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

import java.util.ArrayList;

/**
 * @author Alterovych Ilya
 */
public class InquirerDataGridWidget extends Composite implements IView {

    interface InquirerDataGridWidgetUiBinder
            extends UiBinder<VerticalPanel, InquirerDataGridWidget> {
    }

    private static InquirerDataGridWidgetUiBinder ourUiBinder =
            GWT.create(InquirerDataGridWidgetUiBinder.class);
    private InquirerModel model;
    private boolean isAdminDataGrid;
    private UserDTO loggedInUserDTO;
    private SingleSelectionModel<InquirerDTO> selectionModel;
    private ArrayList<InquirerDTO> inquirerList;
    private ColumnSortEvent.ListHandler<InquirerDTO> sortHandler;
    private ComparatorsHolder comparatorsHolder;
    @UiField(provided = true)
    DataGrid<InquirerDTO> dataGrid;

    @UiConstructor
    public InquirerDataGridWidget() {
    }

    /**
     * @param model модель которую будет отображать таблица
     * @param isAdminDataGrid true - если таблица для админа
     *                        (будет отображаться колонка "опубликован")<br>
     *                        false - усли таблица для юзера
     *                        (не будет отображаться колонка "опубликован")
     */
    public InquirerDataGridWidget(InquirerModel model, boolean isAdminDataGrid) {
        this.model = model;
        this.isAdminDataGrid = isAdminDataGrid;
    }

    public InquirerDataGridWidget(InquirerModel model, boolean isAdminDataGrid,
                                  UserDTO loggedInUserDTO) {
        this(model, isAdminDataGrid);
        this.loggedInUserDTO = loggedInUserDTO;
    }

    private void setupDataGrid() {
        dataGrid = new DataGrid<>(100);
        dataGrid.setVisible(true);
        dataGrid.setEmptyTableWidget(new Image(ImageResources.resources.noData()));
        addColumnSortHandler();
        setSelectionModel();
        initDataGridColumns();
        addCellPreviewHandler();
    }

    private void addCellPreviewHandler() {
        dataGrid.addCellPreviewHandler(new CellPreviewEvent.Handler<InquirerDTO>() {
            @Override
            public void onCellPreview(CellPreviewEvent<InquirerDTO> event) {
                String inquirerDescription = event.getValue().getDescription();
                if ("mouseover".equals(event.getNativeEvent().getType())) {
                    dataGrid.getRowElement(event.getIndex()).getCells().
                            getItem(event.getColumn()).setTitle(inquirerDescription);
                }
            }
        });
    }

    private void initDataGridColumns() {
        ColumnsHolder columnsHolder = new ColumnsHolder(this);

        if (isAdminDataGrid){
            comparatorsHolder = new ComparatorsHolder();
        } else {
            comparatorsHolder = new ComparatorsHolder(loggedInUserDTO);
            sortHandler.setComparator(columnsHolder.getBestResultColumn(),
                    comparatorsHolder.getBestResultComparator());
        }

        sortHandler.setComparator(columnsHolder.getIdColumn(),
                comparatorsHolder.getIdComparator());
        sortHandler.setComparator(columnsHolder.getNameColumn(),
                comparatorsHolder.getNameComparator());
        sortHandler.setComparator(columnsHolder.getDescriptionColumn(),
                comparatorsHolder.getDescriptionComparator());
        sortHandler.setComparator(columnsHolder.getQuestionColumn(),
                comparatorsHolder.getQuestionComparator());
        sortHandler.setComparator(columnsHolder.getPublishedColumn(),
                comparatorsHolder.getPublishedComparator());

        //add CheckBox column to DataGrid
        Column<InquirerDTO, Boolean> checkBoxColumn =
                new Column<InquirerDTO, Boolean>(new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(InquirerDTO inquirerDTO) {
                        return selectionModel.isSelected(inquirerDTO);
                    }
                };
        checkBoxColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        dataGrid.addColumn(checkBoxColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        dataGrid.setColumnWidth(checkBoxColumn, 10, Style.Unit.PCT);
    }

    private void setSelectionModel() {
        selectionModel = new SingleSelectionModel<>
                (new ProvidesKey<InquirerDTO>() {
                    @Override
                    public Object getKey(InquirerDTO inquirerDTO) {
                        return inquirerDTO;
                    }
                });

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                final InquirerDTO inquirerDTO = selectionModel.getSelectedObject();
                //here do something you want to do on change selection
                model.setSelectedInquirerDTO(inquirerDTO);
            }
        });
        dataGrid.setSelectionModel(selectionModel,
                DefaultSelectionEventManager.<InquirerDTO>createCheckboxManager());
        /*//или так (мне так больше нравиться):
        dataGrid.setSelectionModel(selectionModel);*/
    }

    private void addColumnSortHandler() {
        sortHandler = new ColumnSortEvent.ListHandler<InquirerDTO>
                (model.getInquirerDTOs()) {
            @Override
            public void onColumnSort(ColumnSortEvent event) {
                setList(model.getInquirerDTOs());
                super.onColumnSort(event);
                model.setInquirerDTOs((ArrayList<InquirerDTO>) getList());
                refresh();
            }
        };
        dataGrid.addColumnSortHandler(sortHandler);
    }

    public void resetSelection() {
        selectionModel.setSelected(selectionModel.getSelectedObject(), false);
        model.setSelectedInquirerDTO(null);
    }

    @Override
    public void init() {
        setupDataGrid();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {
        refresh(model.getInquirerDTOs());
    }

    public void refresh(ArrayList<InquirerDTO> inquirerDTOs) {
        inquirerList = inquirerDTOs;
        dataGrid.setRowCount(inquirerList.size(), true);
        dataGrid.setRowData(0, inquirerList);
        dataGrid.redraw();
//        Window.alert(String.valueOf(model.getInquirerDTOs()));
    }

    public DataGrid<InquirerDTO> getDataGrid() {
        return dataGrid;
    }



    public boolean isAdminDataGrid() {
        return isAdminDataGrid;
    }

    public UserDTO getLoggedInUserDTO() {
        return loggedInUserDTO;
    }
}