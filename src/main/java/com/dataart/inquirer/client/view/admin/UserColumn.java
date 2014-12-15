package com.dataart.inquirer.client.view.admin;

import com.dataart.inquirer.shared.dto.UserDTO;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.Column;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

/**
 * @author Alterovych Ilya
 */
public abstract class UserColumn extends Column<UserDTO, String>{

    public UserColumn(final DataGrid<UserDTO> dataGrid,
                      final String name, final double width) {
        super(new TextCell());

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
