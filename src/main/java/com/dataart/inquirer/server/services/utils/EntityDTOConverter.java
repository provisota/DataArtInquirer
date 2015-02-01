package com.dataart.inquirer.server.services.utils;

import com.dataart.inquirer.shared.dto.inquirer.AnswerDTO;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.inquirer.QuestionDTO;
import com.dataart.inquirer.shared.dto.user.UserAnswerDTO;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserQuestionDTO;
import com.dataart.inquirer.shared.entity.inquirer.AnswerEntity;
import com.dataart.inquirer.shared.entity.inquirer.InquirerEntity;
import com.dataart.inquirer.shared.entity.inquirer.QuestionEntity;
import com.dataart.inquirer.shared.entity.user.UserAnswerEntity;
import com.dataart.inquirer.shared.entity.user.UserEntity;
import com.dataart.inquirer.shared.entity.user.UserInquirerEntity;
import com.dataart.inquirer.shared.entity.user.UserQuestionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterovych Ilya
 */
public class EntityDTOConverter {
    private static EntityDTOConverter ourInstance = new EntityDTOConverter();

    public static EntityDTOConverter getInstance() {
        return ourInstance;
    }

    private EntityDTOConverter() {
    }

    public UserDTO createUserDTO(UserEntity userEntity){
        if (userEntity == null){
            return null;
        }
        List<UserInquirerEntity> userInquirerEntityList =
                userEntity.getUserInquirerList();
        List<UserInquirerDTO> userInquirerDTOs = new ArrayList<>(
                userInquirerEntityList != null ? userInquirerEntityList.size() : 0);
        if (userInquirerEntityList != null) {
            for (UserInquirerEntity userInquirerEntity : userInquirerEntityList){
                userInquirerDTOs.add(createUserInquirerDTO(userInquirerEntity));
            }
        }
        return new UserDTO(userEntity.getId(), userEntity.getUsername(),
                userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole(),
                userInquirerDTOs, userEntity.isConfirmed(), userEntity.getConfirmId());
    }

    public InquirerDTO createInquirerDTO(InquirerEntity inquirerEntity){
        List<QuestionEntity> questionEntities = inquirerEntity.getQuestionsList();
        List<QuestionDTO> questionDTOs =
                new ArrayList<>(questionEntities != null ? questionEntities.size() : 0);
        if (questionEntities != null) {
            for (QuestionEntity questionEntity : questionEntities) {
                questionDTOs.add(createQuestionDTO(questionEntity));
            }
        }

        List<UserInquirerEntity> userInquirerEntities =
                inquirerEntity.getUserInquirerList();
        List<UserInquirerDTO> userInquirerDTOs =
                new ArrayList<>(userInquirerEntities != null ?
                        userInquirerEntities.size() : 0);
        if (userInquirerEntities != null){
            for (UserInquirerEntity userInquirerEntity : userInquirerEntities){
                userInquirerDTOs.add(createUserInquirerDTO(userInquirerEntity));
            }
        }

        return new InquirerDTO(inquirerEntity.getId(), inquirerEntity.getName(),
                inquirerEntity.getDescription(), inquirerEntity.isPublished(),
                questionDTOs, userInquirerDTOs);
    }

    private QuestionDTO createQuestionDTO(QuestionEntity questionEntity) {
        List<AnswerEntity> answerEntities = questionEntity.getAnswersList();
        List<AnswerDTO> answerDTOs =
                new ArrayList<>(answerEntities != null ? answerEntities.size() : 0);
        if(answerEntities != null){
            for (AnswerEntity answerEntity : answerEntities){
                answerDTOs.add(createAnswerDTO(answerEntity));
            }
        }
        return new QuestionDTO(questionEntity.getId(), questionEntity.getDescription(),
                questionEntity.getAnswerType(), answerDTOs);
    }

    private AnswerDTO createAnswerDTO(AnswerEntity answerEntity) {
        return new AnswerDTO(answerEntity.getId(), answerEntity.getDescription(),
                answerEntity.isRightAnswer());
    }

    public UserInquirerDTO createUserInquirerDTO(UserInquirerEntity userInquirerEntity){
        if (userInquirerEntity == null){
            return null;
        }
        List<UserQuestionEntity> userQuestionEntities =
                userInquirerEntity.getQuestionsList();
        List<UserQuestionDTO> userQuestionDTOs = new ArrayList<>(userQuestionEntities
                != null ? userQuestionEntities.size() : 0);
        if (userQuestionEntities != null) {
            for (UserQuestionEntity userQuestionEntity : userQuestionEntities){
                userQuestionDTOs.add(createUserQuestionDTO(userQuestionEntity));
            }
        }
        UserEntity userEntity = userInquirerEntity.getUserEntity();
        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getUsername(),
                userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole(),
                userEntity.isConfirmed(), userEntity.getConfirmId());

        InquirerEntity inquirerEntity = userInquirerEntity.getInquirerEntity();
        InquirerDTO inquirerDTO = new InquirerDTO(inquirerEntity.getId(),
                inquirerEntity.getName(),inquirerEntity.getDescription(),
                inquirerEntity.isPublished());

         return new UserInquirerDTO(userInquirerEntity.getId(),
                userInquirerEntity.isFinished(), userInquirerEntity.getBestResult(),
                userQuestionDTOs, userDTO, inquirerDTO);
    }

    private UserQuestionDTO createUserQuestionDTO(UserQuestionEntity userQuestionEntity) {
        List<UserAnswerEntity> userAnswerEntities = userQuestionEntity.getAnswersList();
        List<UserAnswerDTO> userAnswerDTOs = new ArrayList<>(
                userAnswerEntities != null ? userAnswerEntities.size() : 0);
        if (userAnswerEntities != null) {
            for (UserAnswerEntity answerEntity : userAnswerEntities){
                userAnswerDTOs.add(createUserAnswerDTO(answerEntity));
            }
        }
        return new UserQuestionDTO(userQuestionEntity.getId(),
                userQuestionEntity.getDescription(), userAnswerDTOs);
    }

    private UserAnswerDTO createUserAnswerDTO(UserAnswerEntity answerEntity) {
        return new UserAnswerDTO(answerEntity.getId(), answerEntity.getDescription(),
                answerEntity.isMarkAsRight());
    }
}
