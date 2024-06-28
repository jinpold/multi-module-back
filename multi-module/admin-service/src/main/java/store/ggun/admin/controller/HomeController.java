package store.ggun.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class HomeController {

    public String Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

    @GetMapping("/admins/test")
    public String hello(){
        return Date + "Welcome To admin service";
    }
    @PostMapping("/name")
    public Map<String, ?> name(@RequestBody Map<String,?> map){
        String name = (String) map.get("name");
        System.out.println("리퀘스트가 가져온 이름 : " +name);
        Map<String,String> respMap = new HashMap<>();
        respMap.put("name", "환영합니다." + name);
        return respMap;
    }


}
