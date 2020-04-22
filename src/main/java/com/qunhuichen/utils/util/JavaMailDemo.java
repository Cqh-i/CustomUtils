package com.qunhuichen.utils.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description: 发送qq邮件
 * 参考教程：https://www.liaoxuefeng.com/wiki/1252599548343744/1319099923693601
 * @author: Cqh-i
 * @create: 2020-04-22 17:17
 */
public class JavaMailDemo {
    private static final Logger logger = LoggerFactory.getLogger(JavaMailDemo.class);
    private static final Properties smtpProperties = new Properties();
    private static Session session = null;

    static {
        initEmailSetting();
    }

    private static void initEmailSetting() {
        // SMTP主机名
        smtpProperties.put("mail.smtp.host", ConfigUtil.getConfig("mail.smtp.host"));
        // 主机端口号
        smtpProperties.put("mail.smtp.port", ConfigUtil.getConfig("mail.smtp.port"));
        // 是否需要用户认证
        smtpProperties.put("mail.smtp.auth", ConfigUtil.getConfig("mail.smtp.auth"));
        // 启用TLS加密
        smtpProperties.put("mail.smtp.starttls.enable", ConfigUtil.getConfig("mail.smtp.starttls.enable"));
        smtpProperties.put("mail.smtp.starttls.required", ConfigUtil.getConfig("mail.smtp.starttls.required"));
        String username = ConfigUtil.getConfig("username");
        String password = ConfigUtil.getConfig("password");
        // 获取Session实例:
        session = Session.getInstance(smtpProperties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // 设置debug模式便于调试:
        session.setDebug(true);
    }

    /**
     * @param subject    邮件主题
     * @param toSomebody 目的邮件地址
     * @param content    邮件内容
     * @throws MessagingException
     * @description: 发送纯文本内容的邮件
     */
    public static void sendEmail(String subject, String toSomebody, String content) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        message.setFrom(new InternetAddress(ConfigUtil.getConfig("username")));
        // 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toSomebody));
        // 设置邮件主题:
        message.setSubject(subject, "UTF-8");
        // 设置邮件正文:
        message.setText(content, "UTF-8");
        // 发送:
        Transport.send(message);
    }

    /**
     * @param subject     邮件主题
     * @param toSomebody  目的邮件地址
     * @param htmlContent 邮件内容
     * @throws MessagingException
     * @description: 发送html内容的邮件
     */
    public static void sendHtmlEmail(String subject, String toSomebody, String htmlContent) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        message.setFrom(new InternetAddress(ConfigUtil.getConfig("username")));
        // 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toSomebody));
        // 设置邮件主题:
        message.setSubject(subject, "UTF-8");
        // 设置邮件正文:
        message.setText(htmlContent, "UTF-8", "html");
        // 发送:
        Transport.send(message);
    }

    /**
     * 发送带附件的邮件
     * @param toSomebody 目的邮件地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param fileName 附件的文件名称
     * @param input 附件
     * @throws MessagingException
     * @throws IOException
     */
    public static void sendEmailWithAttachment(String toSomebody, String subject, String content,
                                               String fileName, InputStream input) throws MessagingException, IOException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(ConfigUtil.getConfig("username")));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toSomebody));
        message.setSubject(subject, "UTF-8");
        Multipart multipart = new MimeMultipart();
        // 添加text:
        BodyPart textpart = new MimeBodyPart();
        textpart.setContent(content, "text/html;charset=utf-8");
        multipart.addBodyPart(textpart);
        // 添加image:
        BodyPart imagepart = new MimeBodyPart();
        imagepart.setFileName(fileName);
        imagepart.setDataHandler(new DataHandler(new ByteArrayDataSource(input, "application/octet-stream")));
        multipart.addBodyPart(imagepart);
        message.setContent(multipart);
        Transport.send(message);
    }

    public static void main(String[] args) throws Exception {
        try {
            //发送目的邮箱
            String toSomebodyEmail = "toSomebody@qq.com";
            sendEmail("testSendEmail", toSomebodyEmail, "你好吖");
            sendHtmlEmail("testhtmlEmail", toSomebodyEmail, "<h1>Hello</h1><p>Hi, xxx</p>");
            try (InputStream input = JavaMailDemo.class.getResourceAsStream("/image/javamail.jpg")) {
                sendEmailWithAttachment(toSomebodyEmail, "Hello Java邮件带附件",
                        "<h1>Hello</h1><p>这是一封带附件的<u>javamail</u>邮件！</p>", "javamail.jpg", input);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
