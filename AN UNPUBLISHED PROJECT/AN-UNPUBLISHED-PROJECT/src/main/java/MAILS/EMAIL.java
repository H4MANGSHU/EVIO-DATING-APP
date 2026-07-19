package MAILS;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EMAIL implements DisposableBean {

    private final JavaMailSender javaMailSender;

    @EventListener
    public void SendEmail(String Email,String name) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,false);
        mimeMessageHelper.setFrom(name);
        mimeMessageHelper.setSubject("Welcome to xyz");
        mimeMessageHelper.setPriority(1);
       var Html="";
       mimeMessageHelper.setText(Html,true);
       javaMailSender.send(message);

    }

    @Override
    public void destroy() throws Exception {
   var user = SecurityContextHolder.getContext();
   log.info("welcome {}",user);
    }
}
