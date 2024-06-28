package store.ggun.account.serviceImpl;

import store.ggun.account.domain.dto.KIvTokenRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KISOpenFeign {

    private final store.ggun.account.service.KoreaInvestmentOpenFeign openFeign;
    private String token;

    @Value("${koreainvestment.key}")
    private String appKey;

    @Value("${koreainvestment.secret}")
    private String appSecret;


    public String getAppToken() {

        this.token = openFeign.getToken(KIvTokenRequestDto.builder()
                .grant_type("client_credentials")
                .appkey(appKey)
                .appsecret(appSecret)
                .build()).getAccess_token();
        return token;
    }


    public String getPrice(String code) {
        log.info("된건가? {} ", openFeign.getPrice("application/json",
                "Bearer "+token,
                appKey,
                appSecret,
                "FHKST01010100"
                , "J"
                , code).getOutput().get("stck_prpr"));
        String price = openFeign.getPrice("application/json",
                "Bearer "+token,
                appKey,
                appSecret,
                "FHKST01010100"
                , "J"
                , code).getOutput().get("stck_prpr").toString();
        return price;
    }
}






