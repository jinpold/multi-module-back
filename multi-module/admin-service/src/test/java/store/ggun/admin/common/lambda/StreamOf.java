package store.ggun.admin.common.lambda;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
public class StreamOf {

    @Test
    public void test(){

        int arr[] = { 1, 2, 3, 4, 5 };
        System.out.println("---------Arrays.stream(arr)------");
        IntStream intStream = Arrays.stream(arr);
        intStream.forEach(str -> System.out.print(str + " "));
        System.out.println("");
        System.out.println("---------stream.of(arr)----------");
        Stream<int[]> stream = Stream.of(arr);
        stream.forEach(str -> System.out.print(str + " "));

        //결과
        //---------Arrays.stream(arr)------
        // 1 2 3 4 5
        //---------stream.of(arr)----------
        // [I@43ee72e6 = 주소 참조값
    }

    @Test
    public void testDifferent(){


        int arr[] = { 1, 2, 3, 4, 5 };
        System.out.println("---------Arrays.stream(arr)------");
        IntStream intStream = Arrays.stream(arr);
        intStream.forEach(str -> System.out.print(str + " "));
        System.out.println("");
        System.out.println("---------stream.of(arr)----------");
        Stream<int[]> stream = Stream.of(arr);
        IntStream intStreamNew = stream.flatMapToInt(Arrays::stream);
        intStreamNew.forEach(str -> System.out.print(str + " "));

        //결과
        //---------Arrays.stream(arr)------
        // 1 2 3 4 5
        //---------stream.of(arr)----------
        // 1 2 3 4 5
    }
}
