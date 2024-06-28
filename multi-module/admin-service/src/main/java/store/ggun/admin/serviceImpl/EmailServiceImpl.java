package store.ggun.admin.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import store.ggun.admin.service.EmailService;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String TO_EMAIL = "sjrkchdkdy@gmail.com";

    public void sendEmail(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(TO_EMAIL); // 관리자 고정 이메일로 전송
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("jinpold@gmail.com"); // 보내는 사람 이메일 (gmail로 설정)
        mailSender.send(message);
    }
}
