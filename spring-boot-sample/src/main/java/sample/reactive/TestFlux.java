package sample.reactive;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class TestFlux {

    List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

//========================================================================================

    // TODO Return an empty Flux
    public Flux<String> emptyFlux() {
        return Flux.empty();
    }

//========================================================================================

    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    public Flux<String> fooBarFluxFromValues() {
        Flux<String> fluxFromValues  =  Flux.just("foo", "bar").log();
        fluxFromValues.subscribe(System.out::println);
        return fluxFromValues;
    }

//========================================================================================

    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    public Flux<String> fooBarFluxFromList() {
        Flux<String> fluxFromIterable = Flux.fromIterable(Arrays.asList("foo", "bar")).log();
        fluxFromIterable.subscribe(System.out::println);
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
        Flux<Long> countFlux = Flux.interval(Duration.ofMillis(100)).take(10).log();
        countFlux.subscribe(iter -> log.info("count is {}", iter));
        return countFlux;
    }

    public void testManyLetters() {


        Flux<String> manyLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string)).log();

        manyLetters.subscribe(w -> log.info(w));

    }

    public void restoringMissingLetter() {
        Mono<String> missing = Mono.just("s");
        Flux<String> allLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .concatWith(missing)
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        allLetters.subscribe(w -> log.info(w));
    }

    public void shortCircuit() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello")
                        .concatWith(Mono.just("world")
                                .delaySubscription(Duration.ofMillis(100)));

        helloPauseWorld.toStream()
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        TestFlux singleton = new TestFlux();
        singleton.emptyFlux();
        singleton.fooBarFluxFromValues();
        singleton.fooBarFluxFromList();
        singleton.errorFlux();
        singleton.counter();
        singleton.testManyLetters();
        singleton.restoringMissingLetter();
        singleton.shortCircuit();
    }
}