package com.dataart.inquirer.client.view.inquirerDataGrid.comparators;

import com.dataart.inquirer.shared.dto.InquirerDTO;

import java.util.Comparator;

/**
 * @author Alterovych Ilya
 */
public class IdComparator implements Comparator<InquirerDTO> {
    @Override
    public int compare(InquirerDTO o1, InquirerDTO o2) {
        return ((Integer)o1.getId()).compareTo(o2.getId());
    }
}
