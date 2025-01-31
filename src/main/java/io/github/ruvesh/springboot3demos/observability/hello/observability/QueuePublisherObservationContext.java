package io.github.ruvesh.springboot3demos.observability.hello.observability;

import io.micrometer.observation.transport.Kind;
import io.micrometer.observation.transport.SenderContext;


public class QueuePublisherObservationContext extends SenderContext<QueueMessage> {
    public QueuePublisherObservationContext(QueueMessage message) {
        super((carrier, key, value) -> {
            if (message != null) {
                message.setStringProperty(key, value);
            }
        }, Kind.PRODUCER);
    }
}
