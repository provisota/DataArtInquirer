package com.dataart.inquirer.server.registration;

import com.dataart.inquirer.shared.dto.user.UserDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Alterovych Ilya
 */
public class UserPasswordResender extends Thread{

    private UserDTO userDTO;
    private String baseUrl;

    public UserPasswordResender(UserDTO userDTO, String baseUrl) {
        this.userDTO = userDTO;
        this.baseUrl = baseUrl;
        start();
    }

    @Override
    public void run() {

        String to = userDTO.getEmail();
        String from = "dataartinquirer@gmail.com";
        String confirmationLink = baseUrl + "confirm.do?confirm_id=" +
                userDTO.getConfirmId() + "&is_resend=true";
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
            message.setSubject("Password recover.");
            message.setContent(
                    "<h1>Восстановление пароля.</h1>" +
                            "<a style=\"border: 1px solid lightblue; " +
                            "border-radius: 4px; padding: 7px; margin-bottom: 10px; " +
                            "background-color: whitesmoke; font-size: large;\" href=" +
                            "\"" + confirmationLink + "\">" +
                            "Восстановить пароль</a><br/><br/>" +
                            "or copy this link:<br/> " + confirmationLink +
                            " <br/>and paste it to browser address bar.",
                    "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Sent message successfully.... UUID = " +
                    userDTO.getConfirmId());
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
