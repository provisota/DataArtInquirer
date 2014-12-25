package com.dataart.inquirer.shared.entity.user;

import com.dataart.inquirer.shared.dto.user.UserAnswerDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alterovych Ilya
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "user_answer")
public class UserAnswerEntity implements Serializable {
    private static final long serialVersionUID = -8099597666231538851L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "is_mark_as_right")
    private boolean isMarkAsRight;

    @ManyToOne
    @JoinColumn(name = "FK_userQuestionId", nullable = false,
            referencedColumnName = "id")
    private UserQuestionEntity userQuestionEntity;

    public UserAnswerEntity() {
    }

    public UserAnswerEntity(boolean isMarkAsRight) {
        this.isMarkAsRight = isMarkAsRight;
    }

    public UserAnswerEntity(UserAnswerDTO answerDTO,
                            UserQuestionEntity userQuestionEntity) {
        this.id = answerDTO.getId();
        this.isMarkAsRight = answerDTO.isMarkAsRight();
        this.userQuestionEntity = userQuestionEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMarkAsRight() {
        return isMarkAsRight;
    }

    public void setMarkAsRight(boolean isMarkAsRight) {
        this.isMarkAsRight = isMarkAsRight;
    }

    public UserQuestionEntity getUserQuestionEntity() {
        return userQuestionEntity;
    }

    public void setUserQuestionEntity(UserQuestionEntity userQuestionEntity) {
        this.userQuestionEntity = userQuestionEntity;
    }

    @Override
    public String toString() {
        return "UserAnswerEntity{" +
                "id=" + id +
                ", isMarkAsRight=" + isMarkAsRight +
                ", userQuestionEntityId=" + userQuestionEntity.getId() +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAnswerEntity that = (UserAnswerEntity) o;

        if (id != that.id) return false;
        //noinspection RedundantIfStatement
        if (isMarkAsRight != that.isMarkAsRight) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isMarkAsRight ? 1 : 0);
        return result;
    }
}
