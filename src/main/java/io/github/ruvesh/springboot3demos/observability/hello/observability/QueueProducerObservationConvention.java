package io.github.ruvesh.springboot3demos.observability.hello.observability;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;

public interface QueueProducerObservationConvention extends ObservationConvention<QueueProducerObservationContext> {

    DefaultQueueProducerObservationConvention DEFAULT_PRODUCER_CONVENTION = new DefaultQueueProducerObservationConvention();

    @Override
    default boolean supportsContext(Observation.Context context) {
        return context instanceof QueueProducerObservationContext;
    }
}
