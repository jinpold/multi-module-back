package store.ggun.account.domain.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@Builder
public class KIvTokenRequestDto {


    private String appkey;
    private String appsecret;
    private String grant_type; //고정값: authorization_code





    public MultiValueMap<String, String> toMultiValueMap() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("appkey",this.appkey);
        parameters.add("appsecret",this.appsecret);
        parameters.add("grant_type",this.grant_type);

        return parameters;
    }
}
