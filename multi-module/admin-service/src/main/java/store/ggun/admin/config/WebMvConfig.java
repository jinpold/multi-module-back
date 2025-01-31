package store.ggun.admin.config;
import store.ggun.admin.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/admins/auth/**")
                .excludePathPatterns("/admins/UserAuth/**")
                .excludePathPatterns("/admins/news/**")
                .excludePathPatterns("/admins/articles/**")
                .excludePathPatterns("/admins/boards/**")
                .excludePathPatterns("/admins/users/**")
                .excludePathPatterns("/admins/admins/**")
                .excludePathPatterns("/admins/transactions/**")
                .excludePathPatterns( "/chat/**")
                .excludePathPatterns("/ws-stomp/**")
                .excludePathPatterns("/chat/room/**")
                .excludePathPatterns("/**")
                .excludePathPatterns("admins/**")
                .excludePathPatterns("/admins/**");
    }
}
