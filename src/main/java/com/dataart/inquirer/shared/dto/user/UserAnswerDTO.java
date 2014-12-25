package com.dataart.inquirer.shared.dto.user;

import java.io.Serializable;

/**
 * @author Alterovych Ilya
 */
public class UserAnswerDTO implements Serializable {
    private static final long serialVersionUID = 4797654395800070760L;
    private int id;
    private boolean isMarkAsRight;

    public UserAnswerDTO() {
    }

    public UserAnswerDTO(int id, boolean isMarkAsRight) {
        this.id = id;
        this.isMarkAsRight = isMarkAsRight;
    }

    public UserAnswerDTO(boolean isMarkAsRight) {
        this.isMarkAsRight = isMarkAsRight;
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

    @Override
    public String toString() {
        return "UserAnswerDTO{" +
                "id=" + id +
                ", isMarkAsRight=" + isMarkAsRight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAnswerDTO answerDTO = (UserAnswerDTO) o;

        if (id != answerDTO.id) return false;
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
