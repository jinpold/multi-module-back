package store.ggun.admin.common.junit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    void print() {
        Item s = new Item();
        String s3 = s.print();
        System.out.println("-->"+s3);
        String s2 = "Hello";
        Assertions.assertEquals(s.print(),"Hello");
    }
    @Test
    void add() {
        Item i = new Item();
        int result = i.add(4,3);
        Assertions.assertEquals(7, result);
    }
}