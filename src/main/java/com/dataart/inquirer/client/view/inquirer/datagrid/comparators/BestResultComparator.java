package com.dataart.inquirer.client.view.inquirer.datagrid.comparators;

import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;

import java.util.Comparator;

/**
 * @author Alterovych Ilya
 */
public class BestResultComparator implements Comparator<InquirerDTO> {
    private UserDTO loggedInUserDTO;

    public BestResultComparator(UserDTO loggedInUserDTO) {
        this.loggedInUserDTO = loggedInUserDTO;
    }

    @Override
    public int compare(InquirerDTO o1, InquirerDTO o2) {
        return getBestPercent(o1).compareTo(getBestPercent(o2));
    }

    private Integer getBestPercent(InquirerDTO inquirerDTO) {
        UserInquirerDTO loggedInUserInquirerDTO = null;
        for (UserInquirerDTO userInquirerDTO : inquirerDTO.getUserInquirerList()){
            if (userInquirerDTO.getUserDTO().equals(loggedInUserDTO)){
                loggedInUserInquirerDTO = userInquirerDTO;
            }
        }

        if (loggedInUserInquirerDTO == null){
            return 0;
        }

        int bestResult = loggedInUserInquirerDTO.getBestResult();
        int questionsCount = loggedInUserInquirerDTO.getQuestionsList().size();

        return (int)((double)bestResult / (double)questionsCount * 100);
    }
}
