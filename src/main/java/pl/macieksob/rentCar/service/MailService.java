package pl.macieksob.rentCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {


    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail(String person,String from, String to, String text, String subject, boolean isHtmlContent) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
        message.setFrom(from,person);
        message.setTo(to);
        message.setText(text,isHtmlContent);
        message.setSubject(subject);
        javaMailSender.send(mimeMessage);
    }

    public void sendMailResetPassword(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
        message.setFrom("macieksob25@gmail.com","SobRent");
        message.setTo(email);
        String content = "<p>Szanowany Panie/Szanowna Pani, </p>";
        content += "Zarejestrowaliśmy prośbę zmiany hasła.";
        content += "<br>";
        content += "Aby zmienić hasło kliknij w poniższy link.";
        content += "<br>";
        content += "<a href=\"" + resetPasswordLink + "\">Zmiana hasła</a>";
        content += "<br>";
        content += "Zignoruj ten e-mail, gdy pamiętasz swoje hasło lub nie wysyłałeś prośby o zmianę hasła.";
        content += "<br>";
        content += "<p>Pozdrawiamy, <br> zespół SobRent!</p>";
        message.setSubject("Resetowanie hasła");
        message.setText(content,true);
        javaMailSender.send(mimeMessage);
    }
}
