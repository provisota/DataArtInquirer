package com.dataart.inquirer.shared.entity.user;

import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserQuestionDTO;
import com.dataart.inquirer.shared.entity.inquirer.InquirerEntity;
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
@Table(name = "user_inquirer")
public class UserInquirerEntity implements Serializable{
    private static final long serialVersionUID = 7074299466716719789L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "is_finished")
    private boolean isFinished;

    @Column(name = "best_result")
    private int bestResult;

    @OneToMany(mappedBy = "userInquirerEntity", cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserQuestionEntity> questionsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "FK_userEntityId", nullable = false,
            referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "FK_inquirerEntityId", nullable = false,
            referencedColumnName = "id")
    private InquirerEntity inquirerEntity;

    public UserInquirerEntity() {
    }

    public UserInquirerEntity(boolean isFinished, int bestResult) {
        this.isFinished = isFinished;
        this.bestResult = bestResult;
    }

    public UserInquirerEntity(UserInquirerDTO inquirerDTO, UserEntity userEntity,
                              InquirerEntity inquirerEntity) {
        this(inquirerDTO);
        this.inquirerEntity = inquirerEntity;
        this.userEntity = userEntity;
    }

    public UserInquirerEntity(UserInquirerDTO inquirerDTO, UserEntity userEntity) {
        this(inquirerDTO);
        this.userEntity = userEntity;
    }

    public UserInquirerEntity(UserInquirerDTO inquirerDTO,
                              InquirerEntity inquirerEntity) {
        this(inquirerDTO);
        this.inquirerEntity = inquirerEntity;
    }

    public UserInquirerEntity(UserInquirerDTO userInquirerDTO) {
        this.id = userInquirerDTO.getId();
        this.isFinished = userInquirerDTO.isFinished();
        this.bestResult = userInquirerDTO.getBestResult();
        this.userEntity = new UserEntity(userInquirerDTO.getUserDTO());
        this.inquirerEntity = new InquirerEntity(userInquirerDTO.getInquirerDTO());

        List<UserQuestionDTO> questionsList = userInquirerDTO.getQuestionsList();
        for (UserQuestionDTO userQuestionDTO : questionsList){
            this.questionsList.add(new UserQuestionEntity(userQuestionDTO, this));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public int getBestResult() {
        return bestResult;
    }

    public void setBestResult(int bestResult) {
        this.bestResult = bestResult;
    }

    public List<UserQuestionEntity> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<UserQuestionEntity> questionsList) {
        this.questionsList = questionsList;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public InquirerEntity getInquirerEntity() {
        return inquirerEntity;
    }

    public void setInquirerEntity(InquirerEntity inquirerEntity) {
        this.inquirerEntity = inquirerEntity;
    }

    @Override
    public String toString() {
        return "UserInquirerEntity{" +
                "id=" + id +
                ", isFinished=" + isFinished +
                ", bestResult=" + bestResult +
                ", userEntityId=" + userEntity.getId() +
                ", inquirerEntityId=" + inquirerEntity.getId() +
                ", questionsList=" + questionsList +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInquirerEntity that = (UserInquirerEntity) o;

        if (bestResult != that.bestResult) return false;
        if (id != that.id) return false;
        //noinspection RedundantIfStatement
        if (isFinished != that.isFinished) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isFinished ? 1 : 0);
        result = 31 * result + bestResult;
        return result;
    }
}
