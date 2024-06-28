package store.ggun.admin.domain.model;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Box<T> {
    private Map<String, T> box;

    private List<String> box2;

    public Box() {
        this.box = new HashMap<>();
    }

    public T put(String key, T value) {
        return box.put(key, value);
    }

    public T get(String key) {
        return box.get(key);
    }

    public int size() {
        return box.size();
    }

    public void put(List<String> keys, Inventory<T> values) {
        box = new HashMap<>();
        for(int i =0; i<box.size(); i++){
            box.put(keys.get(i), values.get(i));
        }
        box.forEach((k, v)-> System.out.println(String.format("%s: %s", k, v)));

    }


}
