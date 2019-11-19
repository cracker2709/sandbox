package sample.reactive;

//generic imports to help with simpler IDEs (ie tech.io)

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */

@Log4j2
public class TestFlux {

    public static void main (String[] args) {
        TestFlux testFlux = new TestFlux();
        Flux<String> emptyFlux = testFlux.emptyFlux();
        Flux<String> valuesFlux = testFlux.fooBarFluxFromValues();
        Flux<String> listFlux = testFlux.fooBarFluxFromList();
        Flux<String> exceptionFlux = testFlux.errorFlux();
        Flux<Long> counterFlux = testFlux.counter();


    }

//========================================================================================

    // TODO Return an empty Flux
    public Flux<String> emptyFlux() {
        return Flux.empty();
    }

//========================================================================================

    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    public Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar").log();
    }

//========================================================================================

    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    public Flux<String> fooBarFluxFromList() {
        return Flux.fromIterable(Arrays.asList("foo", "bar")).log();
    }

//========================================================================================

    // TODO Create a Flux that emits an IllegalStateException
    public Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException("Boo!"));
    }

//========================================================================================

    // TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
    public Flux<Long> counter() {
        return Flux.interval(Duration.ofMillis(100)).take(10).log();
    }

}