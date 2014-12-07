package com.dataart.inquirer.server.dao;

import com.dataart.inquirer.shared.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alterovych Ilya
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
