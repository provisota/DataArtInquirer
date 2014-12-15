package com.dataart.inquirer.client.view.admin.comparators;

/**
 * @author Alterovych Ilya
 */
public class ComparatorsHolder {
    private IdComparator idComparator;
    private UsernameComparator usernameComparator;
    private EmailComparator emailComparator;
    private RoleComparator roleComparator;

    public ComparatorsHolder() {
        idComparator = new IdComparator();
        usernameComparator = new UsernameComparator();
        emailComparator = new EmailComparator();
        roleComparator = new RoleComparator();
    }

    public IdComparator getIdComparator() {
        return idComparator;
    }

    public UsernameComparator getUsernameComparator() {
        return usernameComparator;
    }

    public EmailComparator getEmailComparator() {
        return emailComparator;
    }

    public RoleComparator getRoleComparator() {
        return roleComparator;
    }
}
