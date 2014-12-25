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
    private List<UserAnswerDTO> answersList = new ArrayList<>();

    public UserQuestionDTO() {
    }

    public UserQuestionDTO(int id) {
        this.id = id;
    }

    public UserQuestionDTO(List<UserAnswerDTO> answersList) {
        this.answersList = answersList;
    }

    public UserQuestionDTO(int id, List<UserAnswerDTO> answersList) {
        this.id = id;
        this.answersList = answersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserAnswerDTO> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<UserAnswerDTO> answersList) {
        this.answersList = answersList;
    }

    @Override
    public String toString() {
        return "UserQuestionDTO{" +
                "id=" + id +
                ", answersList=" + answersList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuestionDTO that = (UserQuestionDTO) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
