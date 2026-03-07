import java.time.Duration;
import java.time.LocalDateTime;

public class EntryRecord<V> {
    private final LocalDateTime  expireTime;
    private final ValueRecord<V> valueRecord;

    public EntryRecord(ValueRecord<V> _valueRecord) {
        valueRecord = _valueRecord;
        expireTime = LocalDateTime.now().plus(Duration.ofSeconds(valueRecord.timeToLiveInSeconds()));
    }

    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public ValueRecord<V> getValueRecord() {
        return valueRecord;
    }
}
