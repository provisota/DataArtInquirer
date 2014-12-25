package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.UserService;
import com.dataart.inquirer.server.dao.UserRepository;
import com.dataart.inquirer.server.services.utils.EntityDTOConverter;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.dataart.inquirer.shared.entity.user.UserEntity;
import com.dataart.inquirer.shared.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public void addTestUsers() {
        if (!checkNameExist("user") && !checkEmailExist("user@mail.com")) {
            addUser(new UserDTO("user", "user@mail.com", "user", Role.ROLE_USER));
        }
        if (!checkNameExist("admin") && !checkEmailExist("admin@mail.com")) {
            addUser(new UserDTO("admin", "admin@mail.com", "admin", Role.ROLE_ADMIN));
        }
        if (!checkNameExist("anonymousUser") &&
                !checkEmailExist("anonymousUser@mail.com")) {
            addUser(new UserDTO("anonymousUser", "anonymousUser@mail.com", "admin",
                    Role.ROLE_ADMIN));
        }
    }

    @Override
    public UserDTO getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            System.out.println("Not logged in");
            return null;
        } else {
            return findUserByUsername((String) authentication.getPrincipal());
        }
    }

    private boolean checkEmailExist(String email) {
        boolean isExist = findUserByEmail(email) != null;
        if (isExist) {
            System.out.println("Под эл.почтой  " + email + " уже зарегистрирован пользователь.");
        }
        return isExist;
    }

    private boolean checkNameExist(String username) {
        boolean isExist = findUserByUsername(username) != null;
        if (isExist) {
            System.out.println("Под именем " + username + " уже зарегистрирован пользователь.");
        }
        return isExist;
    }

    public UserDTO createDTO(UserEntity userEntity){
        return EntityDTOConverter.getInstance().createUserDTO(userEntity);
    }
}
