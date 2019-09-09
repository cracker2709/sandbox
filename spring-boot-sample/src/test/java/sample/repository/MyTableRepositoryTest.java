package sample.repository;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sample.domain.MyTableEntity;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { sample.SampleApplication.class , sample.H2Config.class })
@TestPropertySource("classpath:config/application-test.yml")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:bdd/MyTable.sql",config = @SqlConfig(dataSource="dataSourcebddSample" ,transactionManager="bddSampleTransactionManager"))
public class MyTableRepositoryTest {

    @Autowired
    private MyTableRepository myTableRepository;


    @Test
    @Transactional
    public void test_actions_between() {
        // GIVEN
        Date dateMin = Date.from(LocalDateTime.parse("2017-07-28 02:59:59" , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.FRANCE)).toInstant(ZoneOffset.UTC));
        Date dateMax = Date.from(LocalDateTime.parse("2018-07-28 02:59:59" , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.FRANCE)).toInstant(ZoneOffset.UTC));

        // WHEN
        List<MyTableEntity> actionEntities = myTableRepository.findByCreationDateBetween(dateMin, dateMax);

        // THEN
        Assert.assertFalse("Liste Non Vide  =  OK  " , actionEntities.isEmpty());
    }

    @Test
    @Transactional
    public void test_save_action() {
        // GIVEN
        MyTableEntity myTableEntity = new MyTableEntity();
        myTableEntity.setName("test");
        myTableEntity.setAddress("address");
        myTableEntity.setEmail("email");
        myTableEntity.setCreationDate(new Date());

        // WHEN
        myTableRepository.save(myTableEntity);

        // THEN
        try {
            Optional<MyTableEntity> opt = myTableRepository.findById(9L);
            Assert.assertTrue(opt.isPresent());
            MyTableEntity written = opt.get();
            Assert.assertNotNull(written);
            Assert.assertEquals("test", written.getName());
            Assert.assertEquals("address", written.getAddress());
            Assert.assertEquals("email", written.getEmail());
            Assert.assertNotNull(written.getCreationDate());

        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}