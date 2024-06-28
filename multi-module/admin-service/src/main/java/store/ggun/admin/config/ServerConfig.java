package store.ggun.admin.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
@Configuration //설정 클래스 = 빈 객체
public class ServerConfig {

    @Bean
    public String datePattern(){
        return "yyyy-MM-dd 'T' HH:mm:SS";
    }
    @Bean
    public DateFormatter defaultDataFormatter(){
        return new DateFormatter(datePattern()); // 팩토리 패턴 - 리턴에서 new 인스턴스(객체)를 생성함.
    }
}
