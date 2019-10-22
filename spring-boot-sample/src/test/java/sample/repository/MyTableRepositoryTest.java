package sample.repository;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sample.repository.models.MyTableModel;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@TestPropertySource("classpath:config/application-test.yml")
public class MyTableRepositoryTest {

    @Autowired
    private MyTableRepository myTableRepository;


    @Test
    public void test_findById() {

        // GIVEN

        // WHEN
        final Mono<MyTableModel> myTableModelMono = myTableRepository.findById(8L);

        // THEN
        StepVerifier.create(myTableModelMono)
                .expectNextMatches(p -> p.getName().equals("OP04"))
                .expectComplete()
        .verify();

        /*StepVerifier.create(myTableModelMono)
                .expectSubscription()
                .consumeNextWith(entry -> Assert.assertEquals("OP04", entry.getName()))
                .expectComplete()
                .verify();*/

        /*StepVerifier.create(myTableModelMono)
                .expectSubscription()
                .assertNext(myTableModel -> Assert.assertEquals("OP04", myTableModel.getName()))
                .expectComplete()
                .();*/


        /*StepVerifier
                .create(myTableModelMono)
                .expectSubscription()
                .assertNext(myTableModel -> Assert.assertEquals("OP04", myTableModel.getName()))
                .expectComplete()
                .verify();*/



    }


    @Test
    public void test_actions_between() {
        // GIVEN
        Date dateMin = Date.from(LocalDateTime.parse("2017-07-28 02:59:59" , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.FRANCE)).toInstant(ZoneOffset.UTC));
        Date dateMax = Date.from(LocalDateTime.parse("2018-07-28 02:59:59" , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.FRANCE)).toInstant(ZoneOffset.UTC));

        // WHEN
        final Flux<MyTableModel> myTableModelFlux = myTableRepository.findAllCreationDateBetween(dateMin, dateMax);

        // THEN
        StepVerifier
                .create(myTableModelFlux).expectNextCount(8);



    }

    @Test
    public void test_save_action() {
        // GIVEN
        MyTableModel myTableModel = MyTableModel
                .builder()
                .name("test")
                .address("address")
                .email("email")
                .creationDate(new Date())
                .build();

        // WHEN
        Mono<MyTableModel> myTableEntityMono = myTableRepository.save(myTableModel);

        // THEN
        StepVerifier.create(myTableEntityMono).expectNextMatches(name -> name.equals("test"));

    }

}