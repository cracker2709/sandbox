package sample.reactive;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
public class TestMono {

    // TODO Return an empty Mono
    public Mono<String> emptyMono() {
        return Mono.empty();
    }

//========================================================================================

    // TODO Return a Mono that never emits any signal
    public Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

//========================================================================================

    // TODO Return a Mono that contains a "foo" value
    public Mono<String> fooMono() {
        Mono<String> monoStr = Mono.just("foo");
        monoStr.subscribe(p -> log.info(p));
        return monoStr;
    }

//========================================================================================

    // TODO Create a Mono that emits an IllegalStateException
    public Mono<String> errorMono() {
        return Mono.error(new IllegalStateException("bar"));
    }





}
