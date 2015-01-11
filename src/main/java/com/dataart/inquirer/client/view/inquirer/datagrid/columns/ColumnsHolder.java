package com.dataart.inquirer.client.view.inquirer.datagrid.columns;

import com.dataart.inquirer.client.view.inquirer.datagrid.InquirerDataGridWidget;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

/**
 * @author Alterovych Ilya
 */
public class ColumnsHolder {
    private IdColumn idColumn;
    private NameColumn nameColumn;
    private DescriptionColumn descriptionColumn;
    private QuestionColumn questionColumn;
    private PublishedColumn publishedColumn;
    private BestResultColumn bestResultColumn;

    public ColumnsHolder(InquirerDataGridWidget inquirerDataGridWidget) {
        DataGrid<InquirerDTO> dataGrid = inquirerDataGridWidget.getDataGrid();
        UserDTO loggedInUserDTO = inquirerDataGridWidget.getLoggedInUserDTO();

        idColumn = new IdColumn(dataGrid, "Id", "Id опросника", 6, true);
        nameColumn = new NameColumn(dataGrid, "Название", "название опросника", 25,
                false);
        descriptionColumn = new DescriptionColumn(dataGrid, "Описание",
                "описание опросника", 40, false);
        questionColumn = new QuestionColumn(dataGrid, "Кол-во вопросов",
                "количество вопросов", 10, true);
        if (inquirerDataGridWidget.isAdminDataGrid()) {
            publishedColumn = new PublishedColumn(dataGrid, "Опубликован",
                    "опубликован или нет", 15, true);
        } else {
            bestResultColumn = new BestResultColumn(dataGrid, "Результат",
                    "ваш лучший результат", 15, true, loggedInUserDTO);
        }
    }

    public IdColumn getIdColumn() {
        return idColumn;
    }

    public NameColumn getNameColumn() {
        return nameColumn;
    }

    public DescriptionColumn getDescriptionColumn() {
        return descriptionColumn;
    }

    public QuestionColumn getQuestionColumn() {
        return questionColumn;
    }

    public PublishedColumn getPublishedColumn() {
        return publishedColumn;
    }

    public BestResultColumn getBestResultColumn() {
        return bestResultColumn;
    }
}
