package store.ggun.account.config;


import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Configuration
public class IamportConfig {

    @Value("${imp.key}")
    private String iamportKey;

    @Value("${imp.secret}")
    private String iamportSecret;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(iamportKey, iamportSecret);
    }





}


