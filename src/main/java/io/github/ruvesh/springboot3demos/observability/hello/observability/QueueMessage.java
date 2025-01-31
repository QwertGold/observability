package io.github.ruvesh.springboot3demos.observability.hello.observability;

import java.util.Map;
import java.util.TreeMap;

public class QueueMessage {
    Map<String, String> map = new TreeMap<>();

    public void setStringProperty(String key, String value) {
        map.put(key, value);
    }

    public String getStringProperty(String key) {
        return map.get(key);
    }
}
