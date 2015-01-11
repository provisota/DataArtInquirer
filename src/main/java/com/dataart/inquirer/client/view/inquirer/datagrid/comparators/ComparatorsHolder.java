package com.dataart.inquirer.client.view.inquirer.datagrid.comparators;

import com.dataart.inquirer.shared.dto.user.UserDTO;

/**
 * @author Alterovych Ilya
 */
public class ComparatorsHolder {
    private IdComparator idComparator = new IdComparator();
    private NameComparator nameComparator = new NameComparator();
    private DescriptionComparator descriptionComparator = new DescriptionComparator();
    private QuestionComparator questionComparator = new QuestionComparator();
    private PublishedComparator publishedComparator = new PublishedComparator();
    private BestResultComparator bestResultComparator;

    public ComparatorsHolder() {
    }

    public ComparatorsHolder(UserDTO loggedInUserDTO) {
        bestResultComparator = new BestResultComparator(loggedInUserDTO);
    }

    public IdComparator getIdComparator() {
        return idComparator;
    }

    public NameComparator getNameComparator() {
        return nameComparator;
    }

    public DescriptionComparator getDescriptionComparator() {
        return descriptionComparator;
    }

    public QuestionComparator getQuestionComparator() {
        return questionComparator;
    }

    public PublishedComparator getPublishedComparator() {
        return publishedComparator;
    }

    public BestResultComparator getBestResultComparator() {
        return bestResultComparator;
    }
}
