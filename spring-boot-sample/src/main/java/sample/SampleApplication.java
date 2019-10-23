package sample;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import sample.repository.MyTableRepository;
import sample.repository.models.MyTableModel;

@Log4j2
@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories(basePackageClasses = MyTableRepository.class)
public class SampleApplication implements CommandLineRunner {

	@Autowired
	MyTableRepository myTableRepository;

	public static void main(String[] args) {
		SpringApplication.run(sample.SampleApplication.class, args);
	}

	@Override
	public void run(String args[]) throws InterruptedException {
		this.myTableRepository.save(MyTableModel.builder().code("1").name("OP01").email("test1@op01.fr").creationDate("2016-01-05 02:59:59").address("LILLE").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
		this.myTableRepository.save(MyTableModel.builder().code("2").name("OP01").email("test1@op02.fr").creationDate("2017-07-14 02:59:59").address("ARRAS").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
		this.myTableRepository.save(MyTableModel.builder().code("3").name("OP02").email("test1@op02.fr").creationDate("2018-12-17 02:59:59").address("DOUAI").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
		this.myTableRepository.save(MyTableModel.builder().code("4").name("OP02").email("test1@op02.fr").creationDate("2017-09-02 02:59:59").address("LENS").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
		this.myTableRepository.save(MyTableModel.builder().code("5").name("OP03").email("test1@op03.fr").creationDate("2017-07-31 02:59:59").address("VALENCIENNES").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
		this.myTableRepository.save(MyTableModel.builder().code("6").name("OP03").email("test1@op03.fr").creationDate("2018-04-10 02:59:59").address("BETHUNE").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
		this.myTableRepository.save(MyTableModel.builder().code("7").name("OP03").email("test1@op03.fr").creationDate("2019-02-09 02:59:59").address("HENIN-BEAUMONT").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
		this.myTableRepository.save(MyTableModel.builder().code("8").name("OP04").email("test1@op04.be").creationDate("2019-04-19 02:59:59").address("TOURNAI").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));
		this.myTableRepository.save(MyTableModel.builder().code("9").name("OP05").email("test1@op05.fr").creationDate("2019-10-23 17:37:12").address("VILLENEUVE D'ASCQ").build()).log().subscribe(item -> log.info("Inserting {}", item.toString()));

		Thread.sleep(100);
	}

}
