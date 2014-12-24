package com.dataart.inquirer.client.view.admin.columns;

import com.dataart.inquirer.shared.dto.user.UserDTO;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

/**
 * @author Alterovych Ilya
 */
public class ColumnsHolder {
    private IdColumn idColumn;
    private UsernameColumn usernameColumn;
    private EmailColumn emailColumn;
    private RoleColumn roleColumn;

    public ColumnsHolder(DataGrid<UserDTO> dataGrid) {
        idColumn = new IdColumn(dataGrid, "Id", "id пользователя", 24, true);
        usernameColumn = new UsernameColumn(dataGrid, "Логин", "логин пользователя", 24,
                false);
        emailColumn = new EmailColumn(dataGrid, "E-mail", "эл.почта пользователя", 24,
                false);
        roleColumn = new RoleColumn(dataGrid, "Права доступа",
                "права доступа пользователя", 24, false);

    }

    public IdColumn getIdColumn() {
        return idColumn;
    }

    public UsernameColumn getUsernameColumn() {
        return usernameColumn;
    }

    public EmailColumn getEmailColumn() {
        return emailColumn;
    }

    public RoleColumn getRoleColumn() {
        return roleColumn;
    }
}
