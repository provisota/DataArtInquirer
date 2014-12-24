package com.dataart.inquirer.client.view.admin.comparators;

import com.dataart.inquirer.shared.dto.user.UserDTO;

import java.util.Comparator;

/**
 * @author Alterovych Ilya
 */
public class UsernameComparator implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
