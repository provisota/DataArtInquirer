package com.dataart.inquirer.shared.dto;

import java.io.Serializable;

/**
 * @author Alterovych Ilya
 */
@SuppressWarnings("unused")
public class AnswerDTO implements Serializable {
    private static final long serialVersionUID = 7209681629661782638L;
    private int id;
    private String description;
    private boolean isRightAnswer;

    public AnswerDTO() {
    }

    public AnswerDTO(int id, String description, boolean isRightAnswer) {
        this(description, isRightAnswer);
        this.id = id;
    }

    public AnswerDTO(String description, boolean isRightAnswer) {
        this.description = description;
        this.isRightAnswer = isRightAnswer;
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

    public boolean isRightAnswer() {
        return isRightAnswer;
    }

    public void setRightAnswer(boolean isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerDTO answerDTO = (AnswerDTO) o;

        if (id != answerDTO.id) return false;
        if (isRightAnswer != answerDTO.isRightAnswer) return false;
        //noinspection RedundantIfStatement
        if (description != null ? !description.equals(answerDTO.description) : answerDTO.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isRightAnswer ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isRightAnswer=" + isRightAnswer +
                '}';
    }
}
