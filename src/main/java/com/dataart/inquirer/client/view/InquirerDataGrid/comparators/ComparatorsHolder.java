package com.dataart.inquirer.client.view.InquirerDataGrid.comparators;

/**
 * @author Alterovych Ilya
 */
public class ComparatorsHolder {
    private IdComparator idComparator = new IdComparator();
    private NameComparator nameComparator = new NameComparator();
    private DescriptionComparator descriptionComparator = new DescriptionComparator();
    private QuestionComparator questionComparator = new QuestionComparator();
    private PublishedComparator publishedComparator = new PublishedComparator();

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
}
