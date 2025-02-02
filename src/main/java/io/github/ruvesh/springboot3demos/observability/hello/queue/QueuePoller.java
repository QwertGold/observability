package io.github.ruvesh.springboot3demos.observability.hello.queue;

import io.github.ruvesh.springboot3demos.observability.hello.observability.QueueConsumerObservationContext;
import io.github.ruvesh.springboot3demos.observability.hello.observability.QueueObservationDocumentation;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.github.ruvesh.springboot3demos.observability.hello.observability.QueueConsumerObservationConvention.DEFAULT_CONSUMER_CONVENTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueuePoller {

    private final ObservationRegistry registry;
    private final Queue queue;

    @PostConstruct
    public void startThread() {
        Thread.ofPlatform()
                .daemon()
                .uncaughtExceptionHandler((t, e) -> { log.error(e.getMessage(), e); })
                .start(() -> {
                    while (true) {
                        try {
                            QueueMessage message = queue.take();
                            sleep(500);

                            Observation observation = QueueObservationDocumentation.CONSUMER.observation(null,
                                    DEFAULT_CONSUMER_CONVENTION, () -> new QueueConsumerObservationContext(message), this.registry);
                            observation.observe(() -> {
                                System.out.println(message.getMsg());
                            });
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                });
    }

    @SneakyThrows(InterruptedException.class)
    private void sleep(long sleep) {
        Thread.sleep(sleep);
    }


}
