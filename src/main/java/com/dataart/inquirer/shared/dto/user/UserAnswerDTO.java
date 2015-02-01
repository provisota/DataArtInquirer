package com.dataart.inquirer.shared.dto.user;

import java.io.Serializable;

/**
 * @author Alterovych Ilya
 */
public class UserAnswerDTO implements Serializable {
    private static final long serialVersionUID = 4797654395800070760L;
    private int id;
    private String description;
    private boolean isMarkAsRight;

    @SuppressWarnings("UnusedDeclaration")
    public UserAnswerDTO() {
    }

    public UserAnswerDTO(int id, String description, boolean isMarkAsRight) {
        this.id = id;
        this.description = description;
        this.isMarkAsRight = isMarkAsRight;
    }

    public UserAnswerDTO(boolean isMarkAsRight, String description) {
        this.isMarkAsRight = isMarkAsRight;
        this.description = description;
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

    public boolean isMarkAsRight() {
        return isMarkAsRight;
    }

    public void setMarkAsRight(boolean isMarkAsRight) {
        this.isMarkAsRight = isMarkAsRight;
    }

    @Override
    public String toString() {
        return "\nUserAnswerDTO{" +
                "id=" + id +
                ", description=" + description +
                ", isMarkAsRight=" + isMarkAsRight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAnswerDTO answerDTO = (UserAnswerDTO) o;

        if (id != answerDTO.id) return false;
        //noinspection RedundantIfStatement
        if (isMarkAsRight != answerDTO.isMarkAsRight) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isMarkAsRight ? 1 : 0);
        return result;
    }
}
