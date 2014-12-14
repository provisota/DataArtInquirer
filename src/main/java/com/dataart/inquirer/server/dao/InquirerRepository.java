package com.dataart.inquirer.server.dao;

import com.dataart.inquirer.shared.entity.InquirerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alterovych Ilya
 */
public interface InquirerRepository extends JpaRepository<InquirerEntity, Integer> {
}
