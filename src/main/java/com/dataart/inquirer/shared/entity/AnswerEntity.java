package com.dataart.inquirer.shared.entity;

import com.dataart.inquirer.shared.dto.AnswerDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alterovych Ilya
 */
@Entity
@Table(name = "answer")
@SuppressWarnings("unused")
public class AnswerEntity implements Serializable {
    private static final long serialVersionUID = 2264500867513919121L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_right_answer")
    private boolean isRightAnswer;

    @ManyToOne
    @JoinColumn(name = "FK_questionId", nullable = false,
            referencedColumnName = "id")
    private QuestionEntity questionEntity;

    public AnswerEntity() {
    }

    public AnswerEntity(String description, boolean isRightAnswer) {
        this.description = description;
        this.isRightAnswer = isRightAnswer;
    }

    public AnswerEntity(AnswerDTO answerDTO, QuestionEntity questionEntity) {
        this.id = answerDTO.getId();
        this.description = answerDTO.getDescription();
        this.isRightAnswer = answerDTO.isRightAnswer();
        this.questionEntity = questionEntity;
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

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (id != that.id) return false;
        if (isRightAnswer != that.isRightAnswer) return false;
        //noinspection RedundantIfStatement
        if (description != null ? !description.equals(that.description) : that.description != null)
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
        return "AnswerEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isRightAnswer=" + isRightAnswer +
                ", questionEntityId=" + questionEntity.getId() +
                '}';
    }
}
