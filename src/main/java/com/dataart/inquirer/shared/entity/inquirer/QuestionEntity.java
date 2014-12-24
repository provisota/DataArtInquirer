package com.dataart.inquirer.shared.entity.inquirer;

import com.dataart.inquirer.shared.dto.inquirer.AnswerDTO;
import com.dataart.inquirer.shared.dto.inquirer.QuestionDTO;
import com.dataart.inquirer.shared.enums.AnswerType;
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
@Table(name = "question")
public class QuestionEntity implements Serializable {
    private static final long serialVersionUID = 6224609769869395589L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "answerType", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AnswerType answerType;

    @ManyToOne
    @JoinColumn(name = "FK_inquirerId", nullable = false,
            referencedColumnName = "id")
    private InquirerEntity inquirerEntity;

    @OneToMany(mappedBy = "questionEntity", cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<AnswerEntity> answersList = new ArrayList<>();

    public QuestionEntity() {
    }

    public QuestionEntity(String description, AnswerType answerType) {
        this.description = description;
        this.answerType = answerType;
    }

    public QuestionEntity(QuestionDTO questionDTO, InquirerEntity inquirerEntity) {
        this.id = questionDTO.getId();
        this.description = questionDTO.getDescription();
        this.answerType = questionDTO.getAnswerType();
        this.inquirerEntity = inquirerEntity;
        List<AnswerDTO> answerDTOList = questionDTO.getAnswersList();
        for (AnswerDTO answerDTO : answerDTOList){
            answersList.add(new AnswerEntity(answerDTO, this));
        }
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

    public List<AnswerEntity> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<AnswerEntity> answersList) {
        this.answersList = answersList;
    }

    public InquirerEntity getInquirerEntity() {
        return inquirerEntity;
    }

    public void setInquirerEntity(InquirerEntity inquirerEntity) {
        this.inquirerEntity = inquirerEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

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
        return "QuestionEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", answerType=" + answerType +
                ", answersList=" + answersList +
                ", inquirerEntityId=" + inquirerEntity.getId() +
                ", inquirerEntityName=" + inquirerEntity.getName() +
                '}';
    }
}
