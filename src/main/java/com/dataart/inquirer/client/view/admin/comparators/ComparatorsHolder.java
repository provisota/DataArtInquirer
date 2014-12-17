package com.dataart.inquirer.client.view.admin.comparators;

/**
 * @author Alterovych Ilya
 */
public class ComparatorsHolder {
    private IdComparator idComparator = new IdComparator();
    private UsernameComparator usernameComparator = new UsernameComparator();
    private EmailComparator emailComparator = new EmailComparator();
    private RoleComparator roleComparator = new RoleComparator();

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
