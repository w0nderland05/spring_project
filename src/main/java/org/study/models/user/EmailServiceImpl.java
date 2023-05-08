package org.study.models.user;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{


    private final JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to) throws Exception{
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.setFrom(new InternetAddress("studySite@study.com")); //발신자 이메일 주소 (사이트에서 보냄)
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to)); // 받는 대상 (수신자이메일 주소)
        // 여기다가도 사이트 이름 적으면 됨
        message.setSubject("스터디 사이트 회원가입 이메일 인증");            // 제목

        String msgg = "";
        msgg+= "<div style='margin:100px;'>";
        // 여기다가도 사이트 이름 적으면 됨
        msgg+= "<h1> 안녕하세요 OOO입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong></div><br> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");                                //내용
        // 여기다 이메일 쓰고 보내는 사이트 이름 적으면 됨
        message.setFrom(new InternetAddress("properties email쓰세용!","OOO"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for(int i = 0; i < 8; i++){ // 인증코드 8자리
            int index = rnd.nextInt(3); // 0 ~ 2까지 랜덤

            switch (index){
                case 0:
                    key.append((char)((int)(rnd.nextInt(26)) + 97));
                    // a~z 까지 아스키 코드
                    break;
                case 1:
                    key.append((char)((int)(rnd.nextInt(26)) + 65));
                    // A~Z 까지 아스키 코드
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }


    @Override
    public String sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);
        try {
            emailSender.send(message);
        }catch (MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
