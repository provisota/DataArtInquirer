package com.dataart.inquirer.shared.entity;

import com.dataart.inquirer.shared.dto.InquirerDTO;
import com.dataart.inquirer.shared.dto.QuestionDTO;
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
@Table(name = "inquirer")
public class InquirerEntity implements Serializable {
    private static final long serialVersionUID = 4845502952801987828L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_published")
    private boolean isPublished;

    @OneToMany(mappedBy = "inquirerEntity", cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<QuestionEntity> questionsList = new ArrayList<>();

    public InquirerEntity() {
    }

    public InquirerEntity(String name, String description, boolean isPublished) {
        this.name = name;
        this.description = description;
        this.isPublished = isPublished;
    }

    public InquirerEntity(InquirerDTO inquirerDTO) {
        this.id = inquirerDTO.getId();
        this.name = inquirerDTO.getName();
        this.description = inquirerDTO.getDescription();
        this.isPublished = inquirerDTO.isPublished();
        List<QuestionDTO> questionDTOList = inquirerDTO.getQuestionsList();
        for (QuestionDTO questionDTO : questionDTOList){
            questionsList.add(new QuestionEntity(questionDTO, this));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public List<QuestionEntity> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<QuestionEntity> questionsList) {
        this.questionsList = questionsList;
    }

    @Override
    public String toString() {
        return "InquirerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isPublished=" + isPublished +
                ", questionsList=" + questionsList +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InquirerEntity that = (InquirerEntity) o;
        if (id != that.id) return false;
        //noinspection RedundantIfStatement
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
