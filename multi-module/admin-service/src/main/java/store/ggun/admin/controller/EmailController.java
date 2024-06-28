package store.ggun.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.ggun.admin.domain.model.EmailModel;
import store.ggun.admin.serviceImpl.EmailServiceImpl;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {


    private final EmailServiceImpl emailServiceImpl;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailModel emailModel) {
        emailServiceImpl.sendEmail(emailModel.getEnpEmail(), emailModel.getMessage());
        return "이메일이 성공적으로 전송되었습니다.";
    }
}