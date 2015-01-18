package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.UserService;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.dataart.inquirer.shared.enums.Role;
import com.dataart.inquirer.shared.utils.RegExpPatterns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alterovych Ilya
 */
@Controller
public class RegisterServlet {
    private Pattern pattern;
    private Matcher matcher;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public String addNewUser(@RequestParam("username") final String username,
                             @RequestParam("email") final String email,
                             @RequestParam("password") final String password,
                             @RequestParam("confirm_password") final String confirmPassword,
                             Model model) {

        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("confirm_password", confirmPassword);

        //noinspection ConstantConditions
        if (!isValidUserName(model, username) | !isValidEmail(model, email) |
                !isValidPassword(model, password) |
                !isValidConfirmPassword(model, password, confirmPassword)) {
            return "registration";
        }

        if (isUserNameExist(model, username) | isEmailExist(model, email)){
            return "registration";
        }

        userService.addUser(new UserDTO(username, email, password, Role.ROLE_USER, false));
        model.addAttribute("success_message",
                "вам выслано письмо для подтверждения регистрации");
        sendConfirmationEmail(email);
        return "registration";
    }

    @RequestMapping(value = "/confirm.do", method = RequestMethod.POST)
    public String confirmUser(@RequestParam("user_id") String userId,
                              @RequestParam("is_resend") Boolean isResendConfirm) {

        return "login";
    }

    private boolean isEmailExist(Model model, String email) {
        if (userService.findUserByEmail(email) == null){
            return false;
        } else {
            model.addAttribute("error_email", "эта эл.почта уже зарегистрирована");
            return true;
        }
    }

    private boolean isUserNameExist(Model model, String username) {
        if (userService.findUserByUsername(username) == null){
            return false;
        } else {
            model.addAttribute("error_username", "это имя уже зарегистрировано");
            return true;
        }
    }

    private boolean isValidConfirmPassword(Model model, String password,
                                           String confirmPassword) {
        if (confirmPassword != null && !confirmPassword.isEmpty() &&
                confirmPassword.equals(password)) {
            return true;
        } else {
            model.addAttribute("error_confirm_password", "пароли не совпадают");
            return false;
        }
    }

    private boolean isValidPassword(Model model, String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(RegExpPatterns.PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        if (matcher.matches()) {
            return true;
        } else {
            model.addAttribute("error_password", "недопустимый пароль");
            return false;
        }
    }

    private boolean isValidEmail(Model model, String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(RegExpPatterns.EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return true;
        } else {
            model.addAttribute("error_email", "недопустимый адресс почты");
            return false;
        }
    }

    private boolean isValidUserName(Model model, String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(RegExpPatterns.LOGIN_PATTERN);
        matcher = pattern.matcher(username);

        if (matcher.matches()) {
            return true;
        } else {
            model.addAttribute("error_username", "недопустимое имя пользователя");
            return false;
        }
    }

    private void sendConfirmationEmail(String email) {
        //TODO выслать письмо
    }

}
