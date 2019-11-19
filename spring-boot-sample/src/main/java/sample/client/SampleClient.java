package sample.client;

import com.google.common.net.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.repository.models.MyTableModel;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Collections;

public class SampleClient {
    public static void main(String[] args) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

        WebClient.RequestBodySpec uri1 = webClient
                .method(HttpMethod.GET)
                .uri("/table/all");

        WebClient.ResponseSpec response1 = uri1
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(Charset.forName("UTF-8"))
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        response1.bodyToFlux(MyTableModel.class).map(MyTableModel::getName);





    }

}
