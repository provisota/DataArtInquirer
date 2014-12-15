package com.dataart.inquirer.client.view.admin.columns;

import com.dataart.inquirer.shared.dto.UserDTO;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

/**
 * Базовый абстрактный класс для классов описывающих
 * колонки в таблице (DataGrid) юзеров.
 * @author Alterovych Ilya
 */
abstract class AbstractUserColumn extends Column<UserDTO, String> {

    /**
     * конструктор класса
     *
     * @param dataGrid таблица в которую требуется добавить созданную колонку
     * @param name название колонки (отображается в заголовке)
     * @param title всплывающая подсказка (tooltip) для заголовка
     * @param width ширина колонки
     * @param alignCenter выровнять содержимое по центру? 
     */
    public AbstractUserColumn(final DataGrid<UserDTO> dataGrid,
                              final String name, final String title,
                              final int width, boolean alignCenter) {
        super(new TextCell());

        SafeHtml header = new SafeHtml() {
            private static final long serialVersionUID = -7884004246642119410L;

            @Override
            public String asString() {
                return "<p title=\"" + title + "\"; " +
                        "style=\"margin: 0.5em;text-align:center;\">" + name + "</p>";
            }
        };

        if (alignCenter) {
            setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        }
        setSortable(true);
        dataGrid.addColumn(this, header);
        dataGrid.setColumnWidth(this, width, Style.Unit.PCT);
    }
}
