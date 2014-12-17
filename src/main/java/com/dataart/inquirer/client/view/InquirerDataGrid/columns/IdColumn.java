package com.dataart.inquirer.client.view.InquirerDataGrid.columns;

import com.dataart.inquirer.shared.dto.InquirerDTO;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

/**
 * @author Alterovych Ilya
 */
public class IdColumn extends AbstractInquirerColumn {
    /**
     * конструктор класса
     *
     * @param dataGrid    таблица в которую требуется добавить созданную колонку
     * @param name        название колонки (отображается в заголовке)
     * @param title       всплывающая подсказка (tooltip) для заголовка
     * @param width       ширина колонки
     * @param alignCenter выровнять содержимое по центру?
     */
    public IdColumn(DataGrid<InquirerDTO> dataGrid, String name, String title, int width,
                    boolean alignCenter) {
        super(dataGrid, name, title, width, alignCenter);
    }

    @Override
    public String getValue(InquirerDTO inquirerDTO) {
        return String.valueOf(inquirerDTO.getId());
    }
}
