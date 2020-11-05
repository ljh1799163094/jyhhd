package com.example.jyhhd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("EmailController")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/send")
    public String  sendSimpleMail() throws Exception {

        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ljh1799163094@163.com");
            message.setTo("1799163094@qq.com");
            message.setSubject("主题：励志程序员");
            message.setText("一分钟掌握邮件发送");
            javaMailSender.send(message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "send error:" + e.getMessage();
        }
        return "send success";

    }


}
