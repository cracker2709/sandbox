package sample.client;

import com.google.common.net.HttpHeaders;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.repository.models.MyTableModel;

import javax.validation.constraints.Null;
import java.net.ConnectException;

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

        Flux<MyTableModel> myTableModelFlux = webClient
                    .get()
                    .uri("/table/all")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new IllegalStateException("400 Error")))
                    .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new IllegalStateException("500 Error")))
                    .bodyToFlux(MyTableModel.class);


        myTableModelFlux.subscribe(p -> sampleClient.inspectObj(p));


        // Mandatory to see results !! Otherwise, the main thread ends too early
        try {
            long tempo = 200L;
            log.warn("Wait {} ms in order to see results before main Thread ends !", tempo);
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("End");

    }

    private ExchangeFilterFunction logRequest() {
        log.info("sending request....");
        return (clientRequest, next) -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> log.info("{} => {}", name, value)));

            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction logResponseStatus() {
        log.info("retrieving response....");
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
