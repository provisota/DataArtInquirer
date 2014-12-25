package com.dataart.inquirer.client.view.inquirer.datagrid.columns;

import com.dataart.inquirer.client.view.AbstractColumn;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

/**
 * @author Alterovych Ilya
 */
public class NameColumn extends AbstractColumn<InquirerDTO, String> {
    /**
     * конструктор класса
     *
     * @param dataGrid    таблица в которую требуется добавить созданную колонку
     * @param name        название колонки (отображается в заголовке)
     * @param title       всплывающая подсказка (tooltip) для заголовка
     * @param width       ширина колонки
     * @param alignCenter выровнять содержимое по центру?
     */
    public NameColumn(DataGrid<InquirerDTO> dataGrid, String name, String title,
                      int width, boolean alignCenter) {
        super(dataGrid, name, title, width, alignCenter);
    }

    @Override
    public String getValue(InquirerDTO inquirerDTO) {
        return inquirerDTO.getName();
    }
}
