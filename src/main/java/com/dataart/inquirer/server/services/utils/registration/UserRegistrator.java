package com.dataart.inquirer.server.services.utils.registration;

import com.dataart.inquirer.client.services.UserService;
import com.dataart.inquirer.shared.dto.user.UserDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Alterovych Ilya
 */
public class UserRegistrator extends Thread {

    private UserDTO userDTO;
    private UserService userService;
    private String baseUrl;

    public UserRegistrator(UserDTO userDTO, UserService userService, String baseUrl) {
        this.userDTO = userDTO;
        this.userService = userService;
        this.baseUrl = baseUrl;
        start();
    }

    @Override
    public void run() {
        String uuid = UUID.randomUUID().toString();
        userDTO.setConfirmId(uuid);
        userService.addUser(userDTO);

        String to = userDTO.getEmail();
        String from = "dataartinquirer@gmail.com";
        String localHostConfirmationLink = baseUrl + "confirm.do?confirm_id=" +
                uuid + "&is_resend=false";
        final String username = "mailsender2015@gmail.com";
        final String password = "mmmm1978";

        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Please confirm your registration.");
            message.setContent(
                    "<h1>Пожалуйста подтвердите свою регистрацию.</h1>" +
                            "<a style=\"border: 1px solid lightblue; " +
                            "border-radius: 4px; padding: 7px; margin-bottom: 10px; " +
                            "background-color: whitesmoke; font-size: large;\" href=" +
                            "\"" + localHostConfirmationLink + "\">" +
                            "Подтвердить регистрацию</a><br/><br/>" +
                            "or copy this link:<br/> " + localHostConfirmationLink +
                            " <br/>and paste it to browser address bar.",
                    "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Sent message successfully.... UUID = " + uuid);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
