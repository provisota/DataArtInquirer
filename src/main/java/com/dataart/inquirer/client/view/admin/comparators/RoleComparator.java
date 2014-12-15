package com.dataart.inquirer.client.view.admin.comparators;

import com.dataart.inquirer.shared.dto.UserDTO;

import java.util.Comparator;

/**
 * @author Alterovych Ilya
 */
public class RoleComparator implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return o1.getRole().name().compareTo(o2.getRole().name());
    }
}
