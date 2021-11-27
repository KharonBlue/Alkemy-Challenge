
package com.alkemy.disney.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Kharon
 */
@Service
@AllArgsConstructor
public class ServicioCorreo implements EmailSender{
    
    private final static Logger LOGGER = LoggerFactory
            .getLogger(ServicioCorreo.class);
    
    @Autowired
    private final JavaMailSender mailSender;
    
    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirma tu correo");
            helper.setFrom("kharon.estudio.web@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("fallo el enviar el correo", e);
            throw new IllegalStateException("fallo al enviar el correo");
        }
    }

}
