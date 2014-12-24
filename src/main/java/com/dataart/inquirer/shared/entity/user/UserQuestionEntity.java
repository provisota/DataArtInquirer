package com.dataart.inquirer.shared.entity.user;

import com.dataart.inquirer.shared.dto.user.UserQuestionDTO;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterovych Ilya
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "user_question")
public class UserQuestionEntity implements Serializable {
    private static final long serialVersionUID = 5269780774043492449L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "FK_userInquirerId", nullable = false,
            referencedColumnName = "id")
    private UserInquirerEntity userInquirerEntity;

    @OneToMany(mappedBy = "userQuestionEntity", cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserAnswerEntity> answersList = new ArrayList<>();

    public UserQuestionEntity() {
    }

    public UserQuestionEntity(UserQuestionDTO questionDTO,
                              UserInquirerEntity userInquirerEntity) {
        //TODO реализовать
        this.userInquirerEntity = userInquirerEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInquirerEntity getUserInquirerEntity() {
        return userInquirerEntity;
    }

    public void setUserInquirerEntity(UserInquirerEntity userInquirerEntity) {
        this.userInquirerEntity = userInquirerEntity;
    }

    public List<UserAnswerEntity> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<UserAnswerEntity> answersList) {
        this.answersList = answersList;
    }

    @Override
    public String toString() {
        return "UserQuestionEntity{" +
                "id=" + id +
                ", userInquirerEntityId=" + userInquirerEntity.getId() +
                ", answersList=" + answersList +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuestionEntity that = (UserQuestionEntity) o;

        //noinspection RedundantIfStatement
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
