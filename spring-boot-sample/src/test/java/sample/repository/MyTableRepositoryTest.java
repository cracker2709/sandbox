package sample.repository;

import date.DateFormatUtils;
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


@TestPropertySource("classpath:config/application-test.yml")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyTableRepositoryTest {


    @Autowired
    private MyTableRepository myTableRepository;


    private static final int NBDOC = 8;
    private static final String CODE_1 = "1";
    private static final String CODE_2 = "2";
    private static final String CODE_3 = "3";
    private static final String CODE_4 = "4";
    private static final String CODE_5 = "5";
    private static final String CODE_6 = "6";
    private static final String CODE_7 = "7";
    private static final String CODE_8 = "8";
    private static final String CODE_9 = "9";
    private static final String OP04 = "OP04";
    private static final String OP05 = "OP05";
    private static final String TOURNAI = "TOURNAI";
    private static final String CAMBRAI = "CAMBRAI";


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

        String dateString = DateFormatUtils.formatSqlDateToString(c.getTime());


        // WHEN
        final Mono<MyTableModel> myTableModelMono = myTableRepository.findById(CODE_8);

        // THEN
        StepVerifier.create(myTableModelMono)
                .expectSubscription()
                .consumeNextWith(p -> verifyAsserts(p, CODE_8, OP04, dateString, TOURNAI))
                .verifyComplete();

    }


    //@Test
    public void test_actions_between() {
        // GIVEN
        Date dateMin = Date.from(LocalDateTime.parse("2017-07-28 02:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.FRANCE)).toInstant(ZoneOffset.UTC));
        Date dateMax = Date.from(LocalDateTime.parse("2018-07-28 02:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.FRANCE)).toInstant(ZoneOffset.UTC));

        // WHEN
        //final Flux<MyTableModel> myTableModelFlux = myTableRepository.findAllCreationDateBetween(dateMin, dateMax);

        // THEN
        //StepVerifier
        //        .create(myTableModelFlux).expectNextCount(8);


    }

    @Test
    public void test_save_action() {
        // GIVEN
        String creationDate = DateFormatUtils.formatSqlDateToString(new Date());
        MyTableModel myTableModel = MyTableModel
                .builder()
                .code(CODE_9)
                .name(OP05)
                .address(CAMBRAI)
                .email("email@email.fr")
                .creationDate(creationDate)
                .build();

            // WHEN
            Mono<MyTableModel> myTableEntityMono = myTableRepository.save(myTableModel);

            // THEN
            StepVerifier.create(myTableEntityMono)
                    .expectSubscription()
                    .consumeNextWith(p -> verifyAsserts(p, CODE_9, OP05, creationDate, CAMBRAI))
                    .verifyComplete();

    }

    /**
     * Controle generique des assertions
     *
     * @param myTableModel
     * @param id
     * @param name
     * @param creationDate
     * @param address
     */
    private void verifyAsserts(MyTableModel myTableModel, String id, String name, String creationDate, String address) {
        Assert.assertNotNull(myTableModel);
        Assert.assertEquals(id, myTableModel.getCode());
        Assert.assertEquals(name, myTableModel.getName());
        Assert.assertEquals(creationDate, myTableModel.getCreationDate());
        Assert.assertEquals(address, myTableModel.getAddress());
    }

}
