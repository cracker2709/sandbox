package sample.web;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sample.reactive.TestFlux;

@RestController
@RequestMapping("/test")
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
}
