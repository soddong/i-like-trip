package com.ssafy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
@Configuration
public class EmailConfiguration {
    @Bean
    public JavaMailSender mailSender() {//JAVA MAILSENDER 인터페이스를 구현한 객체를 빈으로 등록하기 위함.

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();//JavaMailSender 의 구현체를 생성하고
        mailSender.setHost("smtp.gmail.com");// 속성을 넣기 시작합니다. 이메일 전송에 사용할 SMTP 서버 호스트를 설정
        mailSender.setPort(587);// 587로 포트를 지정
        mailSender.setUsername("sjwstudy04");//구글계정을 넣습니다.
        mailSender.setPassword("gelr giee oujm rbdk");//구글 앱 비밀번호를 넣습니다.
        mailSender.setDefaultEncoding("UTF-8");
        
        Properties javaMailProperties = new Properties();//JavaMail의 속성을 설정하기 위해 Properties 객체를 생성
        javaMailProperties.put("mail.smtp.auth", "true");//smtp 서버에 인증이 필요
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
//        javaMailProperties.put("mail.smtp.connectiontimeout", 5000);
        javaMailProperties.put("mail.smtp.timeout", 5000);
//        javaMailProperties.put("mail.smtp.writetimeout", 5000);
        javaMailProperties.put("mail.debug", "true");//디버깅 정보 출력

        mailSender.setJavaMailProperties(javaMailProperties);//mailSender에 우리가 만든 properties 넣고 

        return mailSender;//빈으로 등록한다.
    }
}