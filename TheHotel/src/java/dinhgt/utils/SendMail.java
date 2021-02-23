/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.utils;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class SendMail {

    static final Logger LOGGER = Logger.getLogger(SendMail.class);

    public static final String HOST_NAME = "smtp.gmail.com";

    public static final int TSL_PORT = 587;

    public static final String APP_EMAIL = "dinh3135134162@gmail.com";

    public static final String APP_PASSWORD = "SHOWmethe1";

    public static String sendOTP(String email) {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String values = Capital_chars + numbers;
        Random rd = new Random();
        String password = "";
        for (int i = 0; i < 6; i++) {
            password += values.charAt(rd.nextInt(values.length()));
        }
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", TSL_PORT);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Welcome to The Hotel");
            message.setText("you are completed to create account The Hotel, Please to comfirm email by enter OTP code: " + password);

            // send message
            Transport.send(message);

        } catch (MessagingException ex) {
            LOGGER.info("MessagingException " + ex.getMessage());
        }
        return password;
    }

    public static String sendResetPassword(String email) {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String values = Capital_chars + numbers;
        Random rd = new Random();
        String link = "";
        for (int i = 0; i < 25; i++) {
            link += values.charAt(rd.nextInt(values.length()));
        }
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", TSL_PORT);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Welcome to The Hotel");
            message.setText("Hi, youi request to change password, please access to link below to reset password  ");
            String url = "http://localhost:8080/TheHotel/reset?txtLink="+link;
            String linkReset = "<a href='"+url+"'>"+url+"</a>";
            message.setContent(linkReset, "text/html; charset=utf-8");
            // send message
            Transport.send(message);

        } catch (MessagingException ex) {
            LOGGER.info("MessagingException " + ex.getMessage());
        }
        return link;
    }
    public static String sendConfirmLink(String email, String orderid) {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String values = Capital_chars + numbers;
        Random rd = new Random();
        String link = "";
        for (int i = 0; i < 25; i++) {
            link += values.charAt(rd.nextInt(values.length()));
        }
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", TSL_PORT);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Welcome to The Hotel");
            message.setText("Hi, You have just completed a reservation, please visit the star link to complete the reservation");
            String url = "http://localhost:8080/TheHotel/confirm?txtLink="+link+"&oid="+orderid;
            String linkReset = "<a href='"+url+"'>"+url+"</a>";
            message.setContent(linkReset, "text/html; charset=utf-8");
            // send message
            Transport.send(message);

        } catch (MessagingException ex) {
            LOGGER.info("MessagingException " + ex.getMessage());
        }
        return link;
    }
}
