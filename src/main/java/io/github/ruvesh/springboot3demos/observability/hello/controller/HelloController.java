package io.github.ruvesh.springboot3demos.observability.hello.controller;

import io.github.ruvesh.springboot3demos.observability.hello.queue.QueueMessage;
import io.github.ruvesh.springboot3demos.observability.hello.queue.MessageSender;
import io.micrometer.common.KeyValues;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private ObservationRegistry registry;

    @GetMapping("")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("/id/{id}")
    public String helloWorldId(@PathVariable String id){
        return "Hello World! - id: " + id;
    }


    @GetMapping("/exception")
    public String exception(){
        throw new IllegalStateException("Something went wrong");
    }

    @GetMapping("/nested")
    public String nested() {
        Observation observation = Observation.start("my.operation", registry);
        try (Observation.Scope scope = observation.openScope()) {
            scope.getCurrentObservation().event(Observation.Event.of("my.event", "look what happened"));
            scope.getCurrentObservation().getContext().addLowCardinalityKeyValues(KeyValues.of("klaus-low", "domain-value"));
            return "done";
        }
        catch (Exception exception) {
            observation.error(exception);
            throw exception;
        }
        finally {
            observation.stop();
        }
    }



    @GetMapping("/message")
    String message(){

        QueueMessage message = QueueMessage.next();
        messageSender.send(message);

        return "Message sent";
    }
}
