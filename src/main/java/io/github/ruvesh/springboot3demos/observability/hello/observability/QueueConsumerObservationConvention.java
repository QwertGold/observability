package io.github.ruvesh.springboot3demos.observability.hello.observability;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;

public class QueueConsumerObservationConvention implements ObservationConvention<QueuePublisherObservationContext> {

    @Override
    public String getName() {
        return "public.queue.consumer";
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return context instanceof QueuePublisherObservationContext;
    }
}
