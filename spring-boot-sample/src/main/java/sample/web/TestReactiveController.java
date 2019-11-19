package sample.web;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.reactive.TestFlux;
import sample.reactive.TestMono;

@RestController
@RequestMapping("/reactive")
@Log4j2
public class TestReactiveController {

    @GetMapping(value = "/fluxString")
    @ResponseStatus(HttpStatus.OK)
    public Flux<String> getFluxString() {
        Flux<String> fluxString = new TestFlux().fooBarFluxFromList();
        return fluxString;
    }

    @GetMapping(value = "/counter")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Long> counter() {
        Flux<Long> fluxCounter = new TestFlux().counter();
        return fluxCounter;
    }

    @GetMapping(value = "/monoString")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> getMonoString() {
        Mono<String> monoString = new TestMono().fooMono();
        return monoString;
    }
}
