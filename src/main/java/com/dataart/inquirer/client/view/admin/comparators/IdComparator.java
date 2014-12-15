package com.dataart.inquirer.client.view.admin.comparators;

import com.dataart.inquirer.shared.dto.UserDTO;

import java.util.Comparator;

/**
 * @author Alterovych Ilya
 */
public class IdComparator implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return ((Integer)o1.getId()).compareTo(o2.getId());
    }
}
