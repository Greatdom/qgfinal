package com.example.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class SendEmailUtil {
    // 生成验证码的方法
    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 生成6位随机数字
        return String.valueOf(code);
    }

    // 发送邮件的方法
    public boolean sendEmail(String recipientEmail, String verificationCode) {
        final String username = "www778005729@outlook.com"; // 发件人邮箱
        final String password = "Aa1341512355"; // 发件人邮箱密码

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com"); // 邮箱SMTP服务器
        props.put("mail.smtp.port", "587"); // 邮箱SMTP端口

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("您的验证码");
            message.setText("您的验证码是: " + verificationCode);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
