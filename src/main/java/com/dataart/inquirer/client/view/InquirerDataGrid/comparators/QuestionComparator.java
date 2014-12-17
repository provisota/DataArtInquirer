package com.dataart.inquirer.client.view.InquirerDataGrid.comparators;

import com.dataart.inquirer.shared.dto.InquirerDTO;

import java.util.Comparator;

/**
 * @author Alterovych Ilya
 */
public class QuestionComparator implements Comparator<InquirerDTO> {
    @Override
    public int compare(InquirerDTO o1, InquirerDTO o2) {
        return ((Integer)o1.getQuestionsList().size())
                .compareTo(o2.getQuestionsList().size());
    }
}
