package com.dataart.inquirer.server.dao;

import com.dataart.inquirer.shared.entity.user.UserInquirerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alterovych Ilya
 */
public interface UserInquirerRepository extends
        JpaRepository<UserInquirerEntity, Integer> {
        UserInquirerEntity findByUserEntityIdAndInquirerEntityId(
                int FK_userEntityId, int FK_inquirerEntityId);
//
//        UserInquirerEntity findByUserEntityIdAndInquirerEntity(
//                int FK_userEntityId, int FK_inquirerEntityId);
}
