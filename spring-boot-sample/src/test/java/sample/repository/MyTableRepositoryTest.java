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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:config/application-test.yml")
public class MyTableRepositoryTest {

    @Autowired
    private MyTableRepository myTableRepository;


    private static final int NBDOC = 8;
    private static final Long ID_1 = 1L;
    private static final Long ID_2 = 2L;
    private static final Long ID_3 = 3L;
    private static final Long ID_4 = 4L;
    private static final Long ID_5 = 5L;
    private static final Long ID_6 = 6L;
    private static final Long ID_7 = 7L;
    private static final Long ID_8 = 8L;
    private static final String OP04 = "OP04";
    private static final String TOURNAI = "TOURNAI";


    @Test
    public void test_findAll() {

        // GIVEN

        // WHEN
        final Flux<MyTableModel> myTableModelFlux = myTableRepository.findAll();

        // THEN
        StepVerifier.create(myTableModelFlux)
                .expectSubscription()
                .expectNextCount(NBDOC)
                .verifyComplete();

    }

    @Test
    public void test_findById() {

        // GIVEN
        Calendar c = Calendar.getInstance();
        // 2019-04-19 02:59:59
        c.set(Calendar.YEAR, 2019);
        c.set(Calendar.MONTH, Calendar.APRIL);
        c.set(Calendar.DAY_OF_MONTH, 19);
        c.set(Calendar.HOUR_OF_DAY, 2);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        // WHEN
        final Mono<MyTableModel> myTableModelMono = myTableRepository.findById(8L);

        // THEN
        StepVerifier.create(myTableModelMono)
                .expectSubscription()
                .consumeNextWith(p -> verifyAsserts(p, ID_8, OP04, c.getTime(), TOURNAI))
                .verifyComplete();

    }


    //@Test
    public void test_actions_between() {
        // GIVEN
        Date dateMin = Date.from(LocalDateTime.parse("2017-07-28 02:59:59" , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.FRANCE)).toInstant(ZoneOffset.UTC));
        Date dateMax = Date.from(LocalDateTime.parse("2018-07-28 02:59:59" , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.FRANCE)).toInstant(ZoneOffset.UTC));

        // WHEN
        //final Flux<MyTableModel> myTableModelFlux = myTableRepository.findAllCreationDateBetween(dateMin, dateMax);

        // THEN
        //StepVerifier
        //        .create(myTableModelFlux).expectNextCount(8);



    }

    //@Test
    public void test_save_action() {
        // GIVEN
        /*MyTableModel myTableModel = MyTableModel
                .builder()
                .name("test")
                .address("address")
                .email("email")
                .creationDate(new Date())
                .build();

        // WHEN
        Mono<MyTableModel> myTableEntityMono = myTableRepository.save(myTableModel);

        // THEN
        StepVerifier.create(myTableEntityMono).expectNextMatches(name -> name.equals("test"));*/

    }

    /**
     * Controle generique des assertions
     * @param myTableModel
     * @param id
     * @param name
     * @param creationDate
     * @param address
     */
    private void verifyAsserts(MyTableModel myTableModel, Long id, String name, Date creationDate, String address) {
        Assert.assertNotNull(myTableModel);
        Assert.assertEquals(id, myTableModel.getId());
        Assert.assertEquals(name, myTableModel.getName());
        Assert.assertEquals(creationDate, myTableModel.getCreationDate());
        Assert.assertEquals(address, myTableModel.getAddress());
    }

}