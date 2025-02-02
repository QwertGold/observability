package io.github.ruvesh.springboot3demos.observability.hello.queue;

import io.github.ruvesh.springboot3demos.observability.hello.observability.QueueObservationDocumentation;
import io.github.ruvesh.springboot3demos.observability.hello.observability.QueueProducerObservationContext;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static io.github.ruvesh.springboot3demos.observability.hello.observability.QueueProducerObservationConvention.DEFAULT_PRODUCER_CONVENTION;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final Queue queue;
    private final ObservationRegistry registry;

    public void send(QueueMessage message) {
        Observation observation = QueueObservationDocumentation.PRODUCER.observation(null,
                DEFAULT_PRODUCER_CONVENTION, () -> new QueueProducerObservationContext(message), this.registry);

        observation.observe(() -> {
            queue.publish(message);
        });
        observation.start();
    }

}
