package io.github.ruvesh.springboot3demos.observability.hello.observability;

import io.micrometer.observation.transport.Kind;
import io.micrometer.observation.transport.ReceiverContext;

public class QueueConsumerObservationContext extends ReceiverContext<QueueMessage> {
    public QueueConsumerObservationContext(QueueMessage receivedMessage) {
        super(QueueMessage::getStringProperty, Kind.CONSUMER);
        setCarrier(receivedMessage);
    }
}
