
package me.lightha.lhc.settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public abstract class Settings<S extends Settings<S>> {
    private final Map<String, Object> values = new HashMap<>();

    public <T> S set(String key, T value) {
        Objects.requireNonNull(key,  "key");
        Objects.requireNonNull(value,"value");
        values.put(key, value);
        return (S) this;
    }

    public <T> T get(String key) {
        return (T) values.get(key);
    }

    public <T> T getOrDefault(String key, T defaultValue) {
        return (T) values.getOrDefault(key, defaultValue);
    }

    public Map<String, Object> asUnmodifiableMap() {
        return Collections.unmodifiableMap(values);
    }
}
