package com.dataart.inquirer.client.view.inquirerDataGrid.comparators;

import com.dataart.inquirer.shared.dto.InquirerDTO;

import java.util.Comparator;

/**
 * @author Alterovych Ilya
 */
public class PublishedComparator implements Comparator<InquirerDTO> {
    @Override
    public int compare(InquirerDTO o1, InquirerDTO o2) {
        return (String.valueOf(o1.isPublished()))
                .compareTo(String.valueOf(o2.isPublished()));
    }
}
