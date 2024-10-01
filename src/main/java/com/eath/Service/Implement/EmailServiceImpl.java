package com.eath.Service.Implement;

import com.eath.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendPasswordResetEmail(String to, String token) {
        String subject = "Réinitialisation de mot de passe";
        String resetUrl = "http://localhost:8081/api/password-reset/reset?token=" + token;

        String text = "Cliquez sur le lien suivant pour réinitialiser votre mot de passe : " + resetUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sowmarieta013@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            mailSender.send(message);
            logger.info("E-mail de réinitialisation de mot de passe envoyé avec succès à: " + to);
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi de l'e-mail de réinitialisation de mot de passe", e);
        }
    }

}
