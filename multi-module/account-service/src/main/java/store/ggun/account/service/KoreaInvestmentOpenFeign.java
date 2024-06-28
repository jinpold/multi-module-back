package store.ggun.account.service;


import store.ggun.account.domain.dto.KIvsPriceResponseDto;
import store.ggun.account.domain.dto.KIvTokenRequestDto;
import store.ggun.account.domain.dto.KIvTokenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "OpenFeignKoreaInvestment", url = "https://openapivts.koreainvestment.com:29443")
public interface KoreaInvestmentOpenFeign {


    @PostMapping("/oauth2/tokenP")
    KIvTokenResponseDto getToken(
            @RequestBody KIvTokenRequestDto requestToken
    );

    @GetMapping("uapi/domestic-stock/v1/quotations/inquire-price")
    KIvsPriceResponseDto getPrice(
            @RequestHeader(name = "Content-Type") String ContentType,
            @RequestHeader(name = "authorization") String authorization,
            @RequestHeader(name = "appKey") String appKey,
            @RequestHeader(name = "appSecret") String appSecret,
            @RequestHeader(name = "tr_id") String tr_id,
            @RequestParam(name = "fid_cond_mrkt_div_code") String fid_cond_mrkt_div_code,
            @RequestParam(name = "fid_input_iscd") String fid_input_iscd
    );

}