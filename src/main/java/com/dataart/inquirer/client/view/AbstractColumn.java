package com.dataart.inquirer.client.view;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

/**
 * @author Alterovych Ilya
 */
abstract public class AbstractColumn <T, V> extends Column<T, String>{

    /**
     * конструктор класса
     *
     * @param dataGrid таблица в которую требуется добавить созданную колонку
     * @param name название колонки (отображается в заголовке)
     * @param title всплывающая подсказка (tooltip) для заголовка
     * @param width ширина колонки
     * @param alignCenter выровнять содержимое по центру?
     */
    public AbstractColumn(final DataGrid<T> dataGrid,
                                  final String name, final String title,
                                  final int width, boolean alignCenter) {
        super(new TextCell());

        SafeHtml header = new SafeHtml() {
            private static final long serialVersionUID = -7874739294151058948L;

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
