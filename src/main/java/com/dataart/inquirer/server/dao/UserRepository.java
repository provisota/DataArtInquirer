package com.dataart.inquirer.server.dao;

import com.dataart.inquirer.shared.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alterovych Ilya
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    UserEntity findByConfirmId(String confirmId);
    List<UserEntity> findByIsConfirmed(Boolean isConfirmed);
}
