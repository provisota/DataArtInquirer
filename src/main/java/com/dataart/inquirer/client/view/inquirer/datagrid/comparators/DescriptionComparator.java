package com.dataart.inquirer.client.view.inquirer.datagrid.comparators;

import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;

import java.util.Comparator;

/**
 * @author Alterovych Ilya
 */
public class DescriptionComparator implements Comparator<InquirerDTO> {
    @Override
    public int compare(InquirerDTO o1, InquirerDTO o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
