package com.dataart.inquirer.client.view.admin.columns;

import com.dataart.inquirer.shared.dto.UserDTO;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;


/**
 * @author Alterovych Ilya
 */
public class IdColumn extends AbstractUserColumn {
    /**
     * конструктор класса
     *
     * @param dataGrid    таблица в которую требуется добавить созданную колонку
     * @param name        название колонки (отображается в заголовке)
     * @param title       всплывающая подсказка (tooltip) для заголовка
     * @param width       ширина колонки
     * @param alignCenter выровнять содержимое по центру?
     */
    public IdColumn(DataGrid<UserDTO> dataGrid, String name, String title, int width,
                    boolean alignCenter) {
        super(dataGrid, name, title, width, alignCenter);
    }

    @Override
    public String getValue(UserDTO userDTO) {
        return String.valueOf(userDTO.getId());
    }
}
