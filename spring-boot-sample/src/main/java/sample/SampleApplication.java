package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import sample.repository.MyTableRepository;


@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories(basePackageClasses = MyTableRepository.class)
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(sample.SampleApplication.class, args);
	}
}
