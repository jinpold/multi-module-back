package store.ggun.admin.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SubstringDemo {

    @Test
    public void 문자열_분할() throws Exception {


        String data = new StringBuilder()
                .append(",d,e,f")
                .insert(0, "a,b,c")
                .toString();
        System.out.println("data : {}" + data);


        String[] arr = data.split(",");
        assertThat(arr.length).isEqualTo(6);

    }

    @Test
    public void personalId() throws Exception {
        String human1 = "970301-1";
        String human2 = "950101-2";
        String human3 = "020101-3";
        String human4 = "020101-4";

        //외국인
        String human5 = "730101-5";
        String human6 = "820101-6";
        String human7 = "120101-7";
        String human8 = "050101-8";


        assertThat(getGender(human1)).isEqualTo("M");


        String[] arr = null;
        // 주민번호를 통해서 나이와 성별(gender)를 출력하시오. (단, 나이는 만 나이로 표기하시오)

    }

    private String getGender(String ssn) {
        // M : 남성, F : 여성
        return switch (ssn.charAt(7)) {
            case '1', '3', '5' -> "M";
            case '2', '4', '6' -> "F";
            default -> "Error";
        };
    }

    @Test
    public void sample() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        assertThat(year).isEqualTo(2024);
        int mouth = now.getMonthValue();
        assertThat(year).isEqualTo(4);
        int day = now.getMonthValue();
        assertThat(year).isEqualTo(24);
    }

    @Test
    public void now() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        assertThat(year).isEqualTo(2024);
        int mouth = now.getMonthValue();
        assertThat(year).isEqualTo(4);
        int day = now.getMonthValue();
        assertThat(year).isEqualTo(24);
    }

    @Test
    public void month() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        assertThat(year).isEqualTo(2024);
        int mouth = now.getMonthValue();
        assertThat(year).isEqualTo(4);
        int day = now.getMonthValue();
        assertThat(year).isEqualTo(24);
    }

    @Test
    public void birthYear() {
        String ssn = "970301-1";
        int birthYear1 = Integer.parseInt(ssn.substring(0, 2));
        var birthYear2 = switch (birthYear1 / 10) {
            case 1, 2 -> birthYear1 + 2000;
            case 3, 4, 5, 6, 7, 8 -> birthYear1 + 1900;
            default -> birthYear1 + 1800;
        };

        assertThat(birthYear2).isEqualTo(1997);

        String ssn2 = "020101-4";
        int birthYear3 = Integer.parseInt(ssn2.substring(0, 2));
        var birthYear4 = switch (birthYear3 / 10) {
            case 1, 2 -> birthYear3 + 2000;
            case 3, 4, 5, 6, 7, 8 -> birthYear3 + 1900;
            default -> birthYear3 + 1800;
        };
        assertThat(birthYear4).isEqualTo(2002);
    }

    @Test
    public void getAge() {

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getMonthValue();

        String ssn = "920425-1";
        int birthYear = Integer.parseInt(ssn.substring(0, 2));

        birthYear = switch (ssn.charAt(7)) {
            case '1', '2', '5', '6' -> birthYear + 2000;
            case '3', '4', '7', '8' -> birthYear + 1900;
            default -> birthYear + 1800;
        };

        int age = year - birthYear;

        int birthMonth = Integer.parseInt(ssn.substring(2, 4));
        int birthDay = Integer.parseInt(ssn.substring(4, 6));
        assertThat(birthMonth - month).isEqualTo(0);
        if (birthMonth > month) {
            age=age-1;
        } else if (birthMonth == month) {
            if (birthDay > day) {
                age--;
            }
        }
        assertThat(age).isEqualTo(31);

    }

//        String ssn = "970301-1";
//        if (birthMonth < month) {
//            age--;
//        }else if (birthMonth == month){
//            if(birthDay < day || day == birthDay){
//                age--;
//            }
//        }
//        assertThat(age).isEqualTo(27);


    @Test
    public void getAgeUsingLamda(){  //최종 로직
        String ssn2 = "920425-1";
        int fullYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        int age2 = Stream.of(ssn2)
                .map(ssn->Integer.parseInt(ssn.substring(0, 2)))
                .map(birthYear-> switch (ssn2.charAt(7)) { // 1992
                    case '1', '2', '5', '6' -> birthYear + 1900;
                    case '3', '4', '7', '8' -> birthYear + 2000;
                    default -> birthYear + 1800;})
                .map(i -> i*10000) // = 19920000
                .map(i -> i+ Integer.parseInt(ssn2.substring(2, 6))) // 19920425
                .map(i -> (fullYear-i)/10000) // 31
                .findFirst()
                .get();

        assertThat(age2).isEqualTo(31);
    }
}







//        String [] arr = {human1,human2,human3,human4,human5};
//        int year = LocalDate.now().getYear();
//        for (int i = 0; i < arr.length; i++) {
//            StringTokenizer st = new StringTokenizer(arr[i],"-");
//            System.out.println(st.nextToken());
//            while (st.hasMoreTokens()){
//                String token = st.nextToken();
//                System.out.print(token);
//                if (token.length() == 1){
//                    int gender = Integer.parseInt(token);
//                    if (gender == 1 || gender == 3){
//                        System.out.println(" 성별은 남자");
//                    } else if (gender == 2 || gender == 4) {
//                        System.out.println(" 성별은 여자");
//                    } else {
//                        System.out.println(" 외국인");
//                    }
//                } else if (token.length() == 6) {
//                    int age = Integer.parseInt(token.substring(0,2));
//                    if (age/10 == 9){
//
//                        age = age + 1900;
//                        System.out.println(" 나이는 = " + (year-age));
//                    } else{
//                        age = age + 2000;
//                        System.out.println(" 나이는 = " + (year-age));
//                    }
//                }
//            }
//        }



