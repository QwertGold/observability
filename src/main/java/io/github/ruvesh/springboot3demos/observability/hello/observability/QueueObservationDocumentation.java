package io.github.ruvesh.springboot3demos.observability.hello.observability;

import io.micrometer.common.KeyValue;
import io.micrometer.common.docs.KeyName;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;
import io.micrometer.observation.docs.ObservationDocumentation;

public enum QueueObservationDocumentation implements ObservationDocumentation {

    PRODUCER {
        @Override
        public Class<? extends ObservationConvention<? extends Observation.Context>> getDefaultConvention() {
            return QueueProducerObservationConvention.class;
        }

        @Override
        public KeyName[] getLowCardinalityKeyNames() {
            return LowCardinalityKeyNames.values();
        }

        @Override
        public KeyName[] getHighCardinalityKeyNames() {
            return HighCardinalityKeyNames.values();
        }
    },
    CONSUMER {
        @Override
        public Class<? extends ObservationConvention<? extends Observation.Context>> getDefaultConvention() {
            return QueueConsumerObservationConvention.class;
        }

        @Override
        public KeyName[] getLowCardinalityKeyNames() {
            return LowCardinalityKeyNames.values();
        }

        @Override
        public KeyName[] getHighCardinalityKeyNames() {
            return HighCardinalityKeyNames.values();
        }

    };

    public enum LowCardinalityKeyNames implements KeyName {

        /**
         * Name of the exception thrown during the operation, or
         * {@value KeyValue#NONE_VALUE} if no exception happened.
         */
        EXCEPTION {
            @Override
            public String asString() {
                return "exception";
            }
        },

        /**
         * Whether the destination (queue or topic) is temporary.
         */
        QUEUE_NAME {
            @Override
            public String asString() {
                return "queue.name";
            }
        }
    }

    public enum HighCardinalityKeyNames implements KeyName {

        /**
         * The key for the user email
         */
        USER_EMAIL {
            @Override
            public String asString() {
                return "user.email";
            }
        },

        /**
         * The public_id of the user
         */
        USER_PUBLIC_ID {
            @Override
            public String asString() {
                return "user.public_id";
            }
        },

        /**
         * The unique id for the message.
         * This can be used to link the producer and consumer in special cases. Normally the trace of the producer and
         * consumer is linked, but in case where sending fails, and we redrive from the DB, the message_id can be used to
         * track the message from producer to consumer
         *
         */
        MESSAGE_ID {
            @Override
            public String asString() {
                return "queue.message_id";
            }
        }

    }

    public static final KeyValue NO_EXCEPTION = KeyValue.of(QueueObservationDocumentation.LowCardinalityKeyNames.EXCEPTION, KeyValue.NONE_VALUE);
    public static final KeyValue NO_QUEUE_NAME= KeyValue.of(QueueObservationDocumentation.LowCardinalityKeyNames.QUEUE_NAME, KeyValue.NONE_VALUE);
    public static final KeyValue NO_MESSAGE_ID = KeyValue.of(QueueObservationDocumentation.HighCardinalityKeyNames.MESSAGE_ID, KeyValue.NONE_VALUE);

}
