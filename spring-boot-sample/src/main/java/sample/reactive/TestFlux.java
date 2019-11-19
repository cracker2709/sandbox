package sample.reactive;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

@Log4j2
public class TestFlux {

//========================================================================================

    // TODO Return an empty Flux
    public Flux<String> emptyFlux() {
        return Flux.empty();
    }

//========================================================================================

    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    public Flux<String> fooBarFluxFromValues() {
        Flux<String> fluxFromValues  =  Flux.just("foo", "bar");
        fluxFromValues.subscribe(p -> log.info(p));
        return fluxFromValues;
    }

//========================================================================================

    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    public Flux<String> fooBarFluxFromList() {
        Flux<String> fluxFromIterable = Flux.fromIterable(Arrays.asList("foo", "bar"));
        fluxFromIterable.subscribe(p -> log.info(p));
        return fluxFromIterable;
    }

//========================================================================================

    // TODO Create a Flux that emits an IllegalStateException
    public Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException("Boo!"));
    }

//========================================================================================

    // TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
    public Flux<Long> counter() {
        Flux<Long> countFlux = Flux.interval(Duration.ofMillis(100)).take(10);
        countFlux.subscribe(c -> log.info(c));
        return countFlux;
    }


}