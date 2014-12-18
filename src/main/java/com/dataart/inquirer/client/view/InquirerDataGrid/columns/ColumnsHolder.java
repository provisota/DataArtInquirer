package com.dataart.inquirer.client.view.inquirerDataGrid.columns;

import com.dataart.inquirer.shared.dto.InquirerDTO;
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

    public ColumnsHolder(DataGrid<InquirerDTO> dataGrid) {
        idColumn = new IdColumn(dataGrid, "Id", "Id опросника", 10, true);
        nameColumn = new NameColumn(dataGrid, "Название", "название опросника", 20,
                false);
        descriptionColumn = new DescriptionColumn(dataGrid, "Описание",
                "описание опросника", 45, false);
        questionColumn = new QuestionColumn(dataGrid, "Кол-во вопросов",
                "количество вопросов", 10, true);
        publishedColumn = new PublishedColumn(dataGrid, "Опубликован",
                "опубликован или нет", 15, true);
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
}
