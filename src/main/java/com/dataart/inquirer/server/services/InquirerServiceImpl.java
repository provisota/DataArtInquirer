package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.InquirerService;
import com.dataart.inquirer.server.dao.InquirerRepository;
import com.dataart.inquirer.server.services.utils.EntityDTOConverter;
import com.dataart.inquirer.shared.dto.inquirer.AnswerDTO;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.inquirer.QuestionDTO;
import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.dataart.inquirer.shared.entity.inquirer.InquirerEntity;
import com.dataart.inquirer.shared.enums.AnswerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterovych Ilya
 */
@Transactional
@Service("inquirer")
public class InquirerServiceImpl implements InquirerService {
    @Autowired
    InquirerRepository inquirerRepository;

    @Override
    public ArrayList<InquirerDTO> getAll() {
        List<InquirerEntity> inquirerEntities = inquirerRepository.findAll();
        ArrayList<InquirerDTO> inquirerDTOs =
                new ArrayList<>(inquirerEntities != null ? inquirerEntities.size() : 0);
        if (inquirerEntities != null){
            for (InquirerEntity inquirerEntity : inquirerEntities){
                inquirerDTOs.add(createInquirerDTO(inquirerEntity));
            }
        }
        return inquirerDTOs;
    }

    @Override
    public InquirerDTO addInquirer(InquirerDTO inquirerDTO) {
        return createInquirerDTO(
                inquirerRepository.saveAndFlush(new InquirerEntity(inquirerDTO)));
    }

    @Override
    public InquirerDTO addTestInquirer() {
        List<AnswerDTO> answerDTOs = new ArrayList<>();
        answerDTOs.add(new AnswerDTO("ответ№1", false));
        answerDTOs.add(new AnswerDTO("ответ№2", true));
        answerDTOs.add(new AnswerDTO("ответ№3", false));

        List<QuestionDTO> questionDTOs = new ArrayList<>();
        questionDTOs.add(new QuestionDTO("вопрос№1", AnswerType.CHECK_BOX, answerDTOs));
        questionDTOs.add(new QuestionDTO("вопрос№2", AnswerType.RADIO_BUTTON, answerDTOs));
        questionDTOs.add(new QuestionDTO("вопрос№3", AnswerType.TEXT_BOX, answerDTOs));

        List<UserInquirerDTO> userInquirerDTOs = new ArrayList<>();

        InquirerDTO inquirerDTO = new InquirerDTO("inquirer", "test inquirer",
                false, questionDTOs, userInquirerDTOs);
        return (addInquirer(inquirerDTO));
    }

    @Override
    public void deleteAllInquirers() {
        inquirerRepository.deleteAll();
    }

    @Override
    public void deleteInquirer(InquirerDTO inquirerDTO) {
        inquirerRepository.delete(new InquirerEntity(inquirerDTO));
    }

    private InquirerDTO createInquirerDTO(InquirerEntity inquirerEntity) {
        return EntityDTOConverter.getInstance().createInquirerDTO(inquirerEntity);
    }
}
