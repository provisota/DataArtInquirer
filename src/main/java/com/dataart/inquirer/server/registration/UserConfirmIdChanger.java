package com.dataart.inquirer.server.registration;

import com.dataart.inquirer.client.services.UserService;
import com.dataart.inquirer.shared.dto.user.UserDTO;

import java.util.UUID;


/**
 * @author Alterovych Ilya
 * Задаёт новый confirmId для переданного в конструктор пользователя,
 * и сохраняет его в базу данных
 */
public class UserConfirmIdChanger extends Thread {
    private UserDTO userDTO;
    private UserService userService;

    public UserConfirmIdChanger(UserDTO userDTO, UserService userService) {
        this.userDTO = userDTO;
        this.userService = userService;
        start();
    }

    @Override
    public void run() {
        String uuid = UUID.randomUUID().toString();
        userDTO.setConfirmId(uuid);
        userService.addUser(userDTO);
    }
}
