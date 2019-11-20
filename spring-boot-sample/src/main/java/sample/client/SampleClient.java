package sample.client;

import com.google.common.net.HttpHeaders;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.repository.models.MyTableModel;

import java.util.List;

@Log4j2
public class SampleClient {
    public static void main(String[] args) {
        SampleClient sampleClient = new SampleClient();

        log.info("init webClient");
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(sampleClient.logRequest())
                .filter(sampleClient.logResponseStatus())
                .build();

        log.info("Process");

        Flux<String> myTableModelFlux = webClient
                    .get()
                    .uri("/table/all")
                    .retrieve()
                    .bodyToFlux(String.class)
                    .onErrorResume(e -> Flux.empty())
                    .doOnError(ex -> log.error("Exception on table calling {} ", ex)).log();


        myTableModelFlux.subscribe(p -> log.info(p));



        log.info("End");

    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> log.info("{} => {}", name, value)));

            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction logResponseStatus() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response Status {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

    private void inspectObj(MyTableModel myTableModel) {
        if(myTableModel == null) {
            log.error("model is null");
        }else {
            log.info("Retrieve model ::  {}", myTableModel.getName());
        }
    }



}
