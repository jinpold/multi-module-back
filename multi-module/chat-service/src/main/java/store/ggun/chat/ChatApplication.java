package store.ggun.chat;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ChatApplication {
//    @RequestMapping("/")
//    public String home() {
//        return "Hello Docker World";
//    }

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
}