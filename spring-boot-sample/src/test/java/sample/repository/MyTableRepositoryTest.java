package sample.repository;

import date.DateFormatUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log4j2
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
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

    @BeforeAll
    public void populateDataTests() {
        this.myTableRepository.deleteAll();
        this.myTableRepository.save(MyTableModel.builder().code("1").name("OP01").email("test1@op01.fr").creationDate("2016-01-05 02:59:59").address("LILLE").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
        this.myTableRepository.save(MyTableModel.builder().code("2").name("OP01").email("test1@op02.fr").creationDate("2017-07-14 02:59:59").address("ARRAS").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
        this.myTableRepository.save(MyTableModel.builder().code("3").name("OP02").email("test1@op02.fr").creationDate("2018-12-17 02:59:59").address("DOUAI").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
        this.myTableRepository.save(MyTableModel.builder().code("4").name("OP02").email("test1@op02.fr").creationDate("2017-09-02 02:59:59").address("LENS").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
        this.myTableRepository.save(MyTableModel.builder().code("5").name("OP03").email("test1@op03.fr").creationDate("2017-07-31 02:59:59").address("VALENCIENNES").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
        this.myTableRepository.save(MyTableModel.builder().code("6").name("OP03").email("test1@op03.fr").creationDate("2018-04-10 02:59:59").address("BETHUNE").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
        this.myTableRepository.save(MyTableModel.builder().code("7").name("OP03").email("test1@op03.fr").creationDate("2019-02-09 02:59:59").address("HENIN-BEAUMONT").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
        this.myTableRepository.save(MyTableModel.builder().code("8").name("OP04").email("test1@op04.be").creationDate("2019-04-19 02:59:59").address("TOURNAI").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
        this.myTableRepository.save(MyTableModel.builder().code("9").name("OP05").email("test1@op05.fr").creationDate("2019-10-23 17:37:12").address("VILLENEUVE D'ASCQ").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));

    }



    @Test
    public void test_findAll() {

        // GIVEN

        // WHEN
        final Flux<MyTableModel> myTableModelFlux = myTableRepository.findAll();

        // THEN
        StepVerifier.create(myTableModelFlux)
                .expectSubscription()
                .expectNextCount(NBDOC)
                .consumeNextWith(p -> log.info(p.toString()))
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
                .consumeNextWith(p -> verifyAssertionss(p, CODE_8, OP04, dateString, TOURNAI))
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
                    .consumeNextWith(p -> verifyAssertionss(p, CODE_9, OP05, creationDate, CAMBRAI))
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
    private void verifyAssertionss(MyTableModel myTableModel, String id, String name, String creationDate, String address) {
        Assertions.assertNotNull(myTableModel);
        Assertions.assertEquals(id, myTableModel.getCode());
        Assertions.assertEquals(name, myTableModel.getName());
        Assertions.assertEquals(creationDate, myTableModel.getCreationDate());
        Assertions.assertEquals(address, myTableModel.getAddress());
    }

}
