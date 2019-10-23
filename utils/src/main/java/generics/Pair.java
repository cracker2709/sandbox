package generics;

import lombok.Data;

@Data
public class Pair<K,V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return String.format("KEY: '%s', VALUE: '%s'", key, value);
    }

}
