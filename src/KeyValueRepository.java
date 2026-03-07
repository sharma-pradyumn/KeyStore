import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class KeyValueRepository<K, V> {

    private final ConcurrentMap<K, EntryRecord<V>> storeMap =
            new ConcurrentHashMap<>();

    public void put(K key, ValueRecord<V> value) {
        storeMap.put(key, new EntryRecord<>(value));
    }

    public EntryRecord<V> get(K key) {
        return storeMap.get(key);
    }

    public void delete(K key) {
        storeMap.remove(key);
    }

    public ConcurrentMap<K, EntryRecord<V>> getStore() {
        return storeMap;
    }
}