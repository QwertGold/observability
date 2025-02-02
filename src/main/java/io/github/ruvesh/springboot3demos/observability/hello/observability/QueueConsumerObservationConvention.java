package io.github.ruvesh.springboot3demos.observability.hello.observability;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;

public interface QueueConsumerObservationConvention extends ObservationConvention<QueueConsumerObservationContext> {


    DefaultQueueConsumerObservationConvention DEFAULT_CONSUMER_CONVENTION = new DefaultQueueConsumerObservationConvention();

    @Override
    default boolean supportsContext(Observation.Context context) {
        return context instanceof QueueConsumerObservationContext;
    }
}
