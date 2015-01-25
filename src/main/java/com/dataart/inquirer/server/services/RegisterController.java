package com.dataart.inquirer.server.services;

import com.dataart.inquirer.client.services.UserService;
import com.dataart.inquirer.server.services.utils.registration.UserConfirmIdChanger;
import com.dataart.inquirer.server.services.utils.registration.UserRegistrator;
import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.dataart.inquirer.shared.enums.Role;
import com.dataart.inquirer.shared.utils.RegExpPatterns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alterovych Ilya
 */
@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class RegisterController {
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

        if (!isValidUserName(model, username) | !isValidEmail(model, email) |
                !isValidPassword(model, password) |
                !isValidConfirmPassword(model, password, confirmPassword)) {
            return "registration";
        }

        if (isUserNameExist(model, username) | isEmailExist(model, email)) {
            return "registration";
        }

        resetForm(model);

        new UserRegistrator(new UserDTO(username, email,
                password, Role.ROLE_USER, false, "fakeConfirmID"),
                userService, getBaseUrl());

        model.addAttribute("success_message",
                "В течении 1 - 3 минут вам прийдёт письмо для подтверждения регистрации");
        return "registration";
    }

    @RequestMapping(value = "/confirm.do", method = RequestMethod.GET)
    public String confirmUser(@RequestParam("confirm_id") String confirmId,
                              @RequestParam("is_resend") boolean isResendConfirm,
                              Model model) {
        resetForm(model);
        UserDTO confirmedUser = userService.findUserByConfirmId(confirmId);
        if (confirmedUser == null) {
            model.addAttribute("error_message",
                    "Такой аккаунт не существует, зарегестрируйтесь пожалуйста");
            return "registration";
        } else {
            confirmedUser.setConfirmed(true);
            userService.editUser(confirmedUser);
            new UserConfirmIdChanger(confirmedUser, userService);
            model.addAttribute("success_message",
                    "Ваш аккаунт успешно подтверждён, теперь вы можете авторизоваться");
            return "login";
        }
    }

    private void resetForm(Model model) {
        model.addAttribute("username", "");
        model.addAttribute("email", "");
        model.addAttribute("password", "");
        model.addAttribute("confirm_password", "");
    }

    private String getBaseUrl() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes());
        HttpServletRequest request = servletRequestAttributes.getRequest();

        /*String baseUrl = request.getRequestURL().toString().
                replaceAll(request.getRequestURI(), "") + request.getContextPath() + "/";*/

        if ((request.getServerPort() == 80) ||
                (request.getServerPort() == 443)) {
            return request.getScheme() + "://" + request.getServerName() +
                    request.getContextPath() + "/";
        } else {
            return request.getScheme() + "://" + request.getServerName() + ":" +
                    request.getServerPort() + request.getContextPath() + "/";
        }
    }

    private boolean isEmailExist(Model model, String email) {
        if (userService.findUserByEmail(email) == null) {
            return false;
        } else {
            model.addAttribute("error_email", "эта эл.почта уже зарегистрирована");
            return true;
        }
    }

    private boolean isUserNameExist(Model model, String username) {
        if (userService.findUserByUsername(username) == null) {
            return false;
        } else {
            model.addAttribute("error_username", "это имя уже зарегистрировано");
            return true;
        }
    }

    private boolean isValidConfirmPassword(Model model, String password,
                                           String confirmPassword) {
        if (confirmPassword != null && confirmPassword.equals(password)) {
            return true;
        } else {
            model.addAttribute("error_confirm_password", "пароли не совпадают");
            return false;
        }
    }

    private boolean isValidPassword(Model model, String password) {
        if (password == null || password.isEmpty()) {
            model.addAttribute("error_password", "недопустимый пароль");
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
            model.addAttribute("error_email", "недопустимый адресс почты");
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
            model.addAttribute("error_username", "недопустимое имя пользователя");
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
}
