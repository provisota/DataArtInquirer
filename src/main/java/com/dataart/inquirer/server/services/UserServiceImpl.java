package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.UserService;
import com.dataart.inquirer.server.dao.UserRepository;
import com.dataart.inquirer.shared.dto.UserDTO;
import com.dataart.inquirer.shared.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
@Transactional
@Service("user")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ArrayList<UserDTO> getAll() {
        ArrayList<UserDTO> userDTOs = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity userEntity : userEntities){
            userDTOs.add(createDTO(userEntity));
        }
        return userDTOs;
    }

    @Override
    public UserDTO editUser(UserDTO userDTO) {
        return addUser(userDTO);
    }

    /**
     * Добавляет нового юзера в БД
     * @param userDTO юзер для сохранения
     * @return возвращает сохранённого юзера
     */
    @Override
    public UserDTO addUser(UserDTO userDTO) {
        return createDTO(userRepository.saveAndFlush(new UserEntity(userDTO)));
    }

    @Override
    public ArrayList<UserDTO> addUserBatch(Set<UserDTO> userDTOs) {
        List<UserEntity> userEntities = new ArrayList<>(userDTOs.size());
        for (UserDTO userDTO : userDTOs){
            userEntities.add(new UserEntity(userDTO));
        }
        List<UserEntity> returnedUserEntities = userRepository.save(userEntities);
        ArrayList<UserDTO> returnedUserDTOs = new ArrayList<>(returnedUserEntities.size());
        for (UserEntity userEntity : returnedUserEntities){
            returnedUserDTOs.add(createDTO(userEntity));
        }
        return returnedUserDTOs;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        return createDTO(userRepository.findByUsername(username));
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return createDTO(userRepository.findByEmail(email));
    }

    public UserDTO createDTO(UserEntity userEntity){
        if (userEntity == null){
            return null;
        }
        return new UserDTO(userEntity.getId(), userEntity.getUsername(),
                userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());
    }
}
