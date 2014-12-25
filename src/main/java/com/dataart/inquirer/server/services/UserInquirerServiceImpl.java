package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.UserInquirerService;
import com.dataart.inquirer.server.dao.UserInquirerRepository;
import com.dataart.inquirer.server.services.utils.EntityDTOConverter;
import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.dataart.inquirer.shared.entity.user.UserInquirerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alterovych Ilya
 */
@Transactional
@Service("user_inquirer")
public class UserInquirerServiceImpl implements UserInquirerService{
    @Autowired
    UserInquirerRepository userInquirerRepository;

    @Override
    public UserInquirerDTO addUserInquirer(UserInquirerDTO userInquirerDTO) {
        return createDTO(userInquirerRepository.saveAndFlush(
                new UserInquirerEntity(userInquirerDTO)));
    }

    @Override
    public UserInquirerDTO editUserInquirer(UserInquirerDTO userInquirerDTO) {
        return addUserInquirer(userInquirerDTO);
    }

    @Override
    public UserInquirerDTO getExistingUserInquirer(int userId, int inquirerId) {
        return createDTO(userInquirerRepository.
                findByUserEntityIdAndInquirerEntityId(userId, inquirerId));
    }

    private UserInquirerDTO createDTO(UserInquirerEntity userInquirerEntity) {
        return EntityDTOConverter.getInstance().createUserInquirerDTO(userInquirerEntity);
    }
}
