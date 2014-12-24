package com.dataart.inquirer.shared.dto.inquirer;

import com.dataart.inquirer.shared.enums.AnswerType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterovych Ilya
 */
@SuppressWarnings("unused")
public class QuestionDTO implements Serializable {
    private static final long serialVersionUID = -2472677463450426023L;
    private int id;
    private String description;
    private AnswerType answerType;
    private List<AnswerDTO> answersList = new ArrayList<>();

    public QuestionDTO() {
    }

    public QuestionDTO(int id, String description, AnswerType answerType,
                       List<AnswerDTO> answersList) {
        this(description, answerType, answersList);
        this.id = id;
    }

    public QuestionDTO(String description, AnswerType answerType,
                       List<AnswerDTO> answersList) {
        this(description, answerType);
        this.answersList = answersList;
    }

    public QuestionDTO(String description, AnswerType answerType) {
        this.description = description;
        this.answerType = answerType;
    }

    public QuestionDTO(int id, String description, AnswerType answerType) {
        this(description, answerType);
        this.id = id;
    }

    public List<AnswerDTO> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<AnswerDTO> answersList) {
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

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDTO that = (QuestionDTO) o;

        if (id != that.id) return false;
        if (answerType != that.answerType) return false;
        //noinspection RedundantIfStatement
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + answerType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nQuestionDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", answerType=" + answerType +
                ", answersList=" + answersList +
                '}';
    }
}
