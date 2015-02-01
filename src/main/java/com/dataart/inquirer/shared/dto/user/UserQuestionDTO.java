package com.dataart.inquirer.shared.dto.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterovych Ilya
 */
public class UserQuestionDTO implements Serializable {
    private static final long serialVersionUID = 4711429049055676069L;
    private int id;
    private String description;
    private List<UserAnswerDTO> answersList = new ArrayList<>();

    @SuppressWarnings("UnusedDeclaration")
    public UserQuestionDTO() {
    }

    public UserQuestionDTO(String description) {
        this.description = description;
    }

    public UserQuestionDTO(int id, String description, List<UserAnswerDTO> answersList) {
        this.id = id;
        this.description = description;
        this.answersList = answersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserAnswerDTO> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<UserAnswerDTO> answersList) {
        this.answersList = answersList;
    }

    @Override
    public String toString() {
        return "\n\nUserQuestionDTO{" +
                "id=" + id +
                ", description=" + description +
                ", answersList=" + answersList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuestionDTO that = (UserQuestionDTO) o;

        //noinspection RedundantIfStatement
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
