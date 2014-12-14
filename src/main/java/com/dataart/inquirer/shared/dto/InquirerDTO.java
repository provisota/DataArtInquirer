package com.dataart.inquirer.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterovych Ilya
 */
@SuppressWarnings("unused")
public class InquirerDTO implements Serializable {
    private static final long serialVersionUID = -1346615492617550251L;
    private int id;
    private String name;
    private String description;
    private boolean isPublished;
    private List<QuestionDTO> questionsList = new ArrayList<>();

    public InquirerDTO() {
    }

    public InquirerDTO(int id, String name, String description,
                       boolean isPublished, List<QuestionDTO> questionsList) {
        this(name, description, isPublished, questionsList);
        this.id = id;
    }

    public InquirerDTO(String name, String description,
                       boolean isPublished, List<QuestionDTO> questionsList) {
        this.name = name;
        this.description = description;
        this.isPublished = isPublished;
        this.questionsList = questionsList;
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

    public List<QuestionDTO> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<QuestionDTO> questionsList) {
        this.questionsList = questionsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InquirerDTO that = (InquirerDTO) o;
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

    @Override
    public String toString() {
        return "InquirerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isPublished=" + isPublished +
                ", questionsList=" + questionsList +
                '}';
    }
}
