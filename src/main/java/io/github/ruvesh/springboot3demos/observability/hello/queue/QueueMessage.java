package io.github.ruvesh.springboot3demos.observability.hello.queue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@ToString
@Getter
@RequiredArgsConstructor
public class QueueMessage {

    static AtomicInteger nextId = new AtomicInteger();

    private final String id;
    private final String destination;
    private final String msg;

    Map<String, String> map = new TreeMap<>();

    public static QueueMessage next() {
        int id = QueueMessage.nextId.getAndIncrement();
        return new QueueMessage(String.valueOf(id), "destination",  String.format("message-%d", id));
    }

    public void setStringProperty(String key, String value) {
        map.put(key, value);
    }

    public String getStringProperty(String key) {
        return map.get(key);
    }
}
