import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class KeyableMap<T, K, V extends Keyable<K>> implements Keyable<T> {
    
    protected final T key;
    protected Map<K, V> map;

    public KeyableMap(T key) {
        this.key = key;
        map = new HashMap<K, V>();
    }

    @Override
    public String toString() {
        String temp = map.values().toString();
        temp = temp.substring(1, temp.length() - 1);
        return key + ": {" + temp + "}";
    }

    @Override
    public T getKey() {
        return key;
    }

    public Optional<V> get(K k) {
        return Optional.ofNullable(map.get(k));
    }

    public KeyableMap<T, K, V> put(V item) {
        map.put(item.getKey(), item);
        return this;
    }
}
