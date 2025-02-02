package io.github.ruvesh.springboot3demos.observability.hello.observability;

import io.github.ruvesh.springboot3demos.observability.hello.queue.QueueMessage;
import io.micrometer.observation.transport.Kind;
import io.micrometer.observation.transport.SenderContext;


public class QueueProducerObservationContext extends SenderContext<QueueMessage> {
    public QueueProducerObservationContext(QueueMessage message) {
        super((carrier, key, value) -> {
            if (message != null) {
                message.setStringProperty(key, value);
            }
        }, Kind.PRODUCER);
        setCarrier(message);
    }
}
