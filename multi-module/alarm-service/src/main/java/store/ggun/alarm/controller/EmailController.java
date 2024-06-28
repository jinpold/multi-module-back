package store.ggun.alarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import store.ggun.alarm.domain.dto.EmailDto;
import store.ggun.alarm.serviceImpl.EmailServiceImpl;

@RestController
@RequestMapping("/Admin-email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailServiceImpl emailServiceImpl;

    @PostMapping("/Admin-send")
    public Mono<ResponseEntity<String>> sendMail(@RequestBody EmailDto emailDto) {
        return emailServiceImpl.sendEmail(emailDto.getEmail(), emailDto.getSubject(), emailDto.getMessage())
                .thenReturn(ResponseEntity.ok("Email sent successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to send mail: " + e.getMessage())));
    }

    @PostMapping("/Admin-send-all")
    public Mono<ResponseEntity<String>> sendAllMail(@RequestBody EmailDto emailDto) {
        return emailServiceImpl.sendAllMail(emailDto.getRecipients(), emailDto.getSubject(), emailDto.getMessage());
    }
}
