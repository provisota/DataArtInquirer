package com.dataart.inquirer.client.view;

import com.dataart.inquirer.shared.dto.UserDTO;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.Column;
import org.gwtbootstrap3.client.ui.DataGrid;

/**
 * @author Alterovych Ilya
 */
public abstract class UserColumn extends Column<UserDTO, String>{
    private final String name;
    private final double width;

    public UserColumn(final DataGrid<UserDTO> dataGrid,
                      final String name, final double width) {
        super(new TextCell());
        this.name = name;
        this.width = width;
        SafeHtml header = new SafeHtml() {
            private static final long serialVersionUID = -7027883410214416610L;

            @Override
            public String asString() {
                return "<p style=\"margin: 0.5em;text-align:center;\">" + name + "</p>";
            }
        };
//        setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER); //not works!(((
        dataGrid.addColumn(this, header);
        dataGrid.setColumnWidth(this, width, Style.Unit.PCT);
    }
}
