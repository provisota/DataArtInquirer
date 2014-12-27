package com.dataart.inquirer.shared.dto.user;

import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterovych Ilya
 */
public class UserInquirerDTO implements Serializable {
    private static final long serialVersionUID = 6722012265121950533L;
    private int id;
    private boolean isFinished;
    private int bestResult;
    private List<UserQuestionDTO> questionsList = new ArrayList<>();
    private UserDTO userDTO;
    private InquirerDTO inquirerDTO;

    public UserInquirerDTO() {
    }

    public UserInquirerDTO(boolean isFinished, int bestResult,
                           List<UserQuestionDTO> questionsList,
                           UserDTO userDTO, InquirerDTO inquirerDTO) {
        this(isFinished, bestResult, userDTO, inquirerDTO);
        this.questionsList = questionsList;
    }

    public UserInquirerDTO(int id, boolean isFinished, int bestResult,
                           List<UserQuestionDTO> questionsList, UserDTO userDTO,
                           InquirerDTO inquirerDTO) {
        this(isFinished, bestResult, questionsList, userDTO, inquirerDTO);
        this.id = id;
    }

    public UserInquirerDTO(boolean isFinished, int bestResult,
                           UserDTO userDTO, InquirerDTO inquirerDTO) {
        this.isFinished = isFinished;
        this.bestResult = bestResult;
        this.userDTO = userDTO;
        this.inquirerDTO = inquirerDTO;
    }

    public UserInquirerDTO(int id, boolean isFinished, int bestResult,
                           UserDTO userDTO, InquirerDTO inquirerDTO) {
        this(isFinished, bestResult, userDTO, inquirerDTO);
        this.id = id;
    }

    public UserInquirerDTO(int id, boolean isFinished, int bestResult,
                           List<UserQuestionDTO> questionsList) {
        this.id = id;
        this.isFinished = isFinished;
        this.bestResult = bestResult;
        this.questionsList = questionsList;
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

    public List<UserQuestionDTO> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<UserQuestionDTO> questionsList) {
        this.questionsList = questionsList;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public InquirerDTO getInquirerDTO() {
        return inquirerDTO;
    }

    public void setInquirerDTO(InquirerDTO inquirerDTO) {
        this.inquirerDTO = inquirerDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInquirerDTO that = (UserInquirerDTO) o;

        if (bestResult != that.bestResult) return false;
        if (id != that.id) return false;
        if (isFinished != that.isFinished) return false;
        if (!inquirerDTO.equals(that.inquirerDTO)) return false;
        if (!userDTO.equals(that.userDTO)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isFinished ? 1 : 0);
        result = 31 * result + bestResult;
        result = 31 * result + userDTO.hashCode();
        result = 31 * result + inquirerDTO.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserInquirerDTO{" +
                "id=" + id +
                ", isFinished=" + isFinished +
                ", bestResult=" + bestResult +
                ", questionsList=" + questionsList +
                ", userDTOId=" + userDTO.getId() +
                ", inquirerDTOId=" + inquirerDTO.getId() +
                "}\n";
    }
}
