package io.github.ruvesh.springboot3demos.observability.hello.queue;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

@Component
public class Queue {

    private final ArrayBlockingQueue<QueueMessage> queue = new ArrayBlockingQueue<>(1000);

    public void publish(QueueMessage message) {
        boolean added = queue.add(message);
        System.out.println(added);
    }

    @SneakyThrows(InterruptedException.class)
    public QueueMessage take() {
        return queue.take();
    }
}
