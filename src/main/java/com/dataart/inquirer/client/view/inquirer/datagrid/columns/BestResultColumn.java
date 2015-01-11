package com.dataart.inquirer.client.view.inquirer.datagrid.columns;

import com.dataart.inquirer.client.view.AbstractColumn;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

/**
 * @author Alterovych Ilya
 */
public class BestResultColumn extends AbstractColumn<InquirerDTO, String> {
    private UserDTO userDTO;

    /**
     * конструктор класса
     *
     * @param dataGrid    таблица в которую требуется добавить созданную колонку
     * @param name        название колонки (отображается в заголовке)
     * @param title       всплывающая подсказка (tooltip) для заголовка
     * @param width       ширина колонки
     * @param alignCenter выровнять содержимое по центру?
     */
    public BestResultColumn(DataGrid<InquirerDTO> dataGrid, String name, String title,
                            int width, boolean alignCenter, UserDTO userDTO) {
        super(dataGrid, name, title, width, alignCenter);
        this.userDTO = userDTO;
    }

    @Override
    public String getValue(InquirerDTO inquirerDTO) {
        UserInquirerDTO loggedInUserInquirerDTO = null;
        for (UserInquirerDTO userInquirerDTO : inquirerDTO.getUserInquirerList()){
            if (userInquirerDTO.getUserDTO().equals(userDTO)){
                loggedInUserInquirerDTO = userInquirerDTO;
            }
        }

        if (loggedInUserInquirerDTO == null){
            return String.valueOf(0);
        }

        int bestResult = loggedInUserInquirerDTO.getBestResult();
        int questionsCount = loggedInUserInquirerDTO.getQuestionsList().size();

        return bestResult + "/" + questionsCount + " (" +
                (int)((double)bestResult / (double)questionsCount * 100) + "%)";
    }
}
