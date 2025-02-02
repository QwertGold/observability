package io.github.ruvesh.springboot3demos.observability.hello.observability;

import io.github.ruvesh.springboot3demos.observability.hello.queue.QueueMessage;
import io.micrometer.common.KeyValue;
import io.micrometer.common.KeyValues;

import static io.github.ruvesh.springboot3demos.observability.hello.observability.QueueObservationDocumentation.*;

public class DefaultQueueProducerObservationConvention implements QueueProducerObservationConvention {


    @Override
    public String getName() {
        return "public.queue.producer";
    }

    @Override
    public KeyValues getLowCardinalityKeyValues(QueueProducerObservationContext context) {
        return KeyValues.of(queueName(context), exception(context));
    }


    @Override
    public KeyValues getHighCardinalityKeyValues(QueueProducerObservationContext context) {
        return KeyValues.of(messageId(context));
    }

    private KeyValue messageId(QueueProducerObservationContext context) {
        QueueMessage message = context.getCarrier();
        if (message != null) {
            return KeyValue.of(QueueObservationDocumentation.HighCardinalityKeyNames.MESSAGE_ID, message.getId());
        }
        return NO_MESSAGE_ID;
    }

    private KeyValue queueName(QueueProducerObservationContext context) {
        QueueMessage message = context.getCarrier();
        if (message != null) {
            return KeyValue.of(QueueObservationDocumentation.LowCardinalityKeyNames.QUEUE_NAME, message.getDestination());
        }
        return NO_QUEUE_NAME;
    }

    private KeyValue exception(QueueProducerObservationContext context) {
        Throwable error = context.getError();
        if (error != null) {
            String simpleName = error.getClass().getSimpleName();
            return KeyValue.of(QueueObservationDocumentation.LowCardinalityKeyNames.EXCEPTION,
                    !simpleName.isEmpty() ? simpleName : error.getClass().getName());
        }
        return NO_EXCEPTION;
    }
}
