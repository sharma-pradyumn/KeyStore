import java.util.Optional;
public class KeyValueStoreService<K, V> {

    private final KeyValueRepository<K, V> repository;

    public KeyValueStoreService(KeyValueRepository<K, V> repository) {
        this.repository = repository;
    }

    public void put(K key, ValueRecord<V> value) {
        repository.put(key, value);
    }

    public Optional<V> get(K key) {

        EntryRecord<V> entry =
                repository.getStore().compute(key, (k, existing) -> {
                    if (existing == null) {
                        return null;
                    }
                    if (existing.isExpired()) {
                        return null; // remove expired entry
                    }
                    return existing;
                });

        if (entry == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(entry.getValueRecord().value());
    }

    public void delete(K key) {
        repository.delete(key);
    }
}
