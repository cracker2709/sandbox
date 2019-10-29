package sample;

import com.github.javafaker.Faker;
import date.DateFormatUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.repository.MyTableRepository;
import sample.repository.models.MyTableModel;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories(basePackageClasses = MyTableRepository.class)
public class SampleApplication {

	@Autowired
	MyTableRepository myTableRepository;

	public static void main(String[] args) {
		SpringApplication.run(sample.SampleApplication.class, args);
	}



	/**
	 * generateCatalogData
	 *
	 * @return CommandLineRunner
	 */
	@Bean
	@Profile("!test")
	public CommandLineRunner generateCatalogData() {
		final int NB = 1000;
		return strings -> {

			List<MyTableModel> tableModelList = new ArrayList<MyTableModel>(NB);
			Faker faker = new Faker();
			String name, firstName;
			StringBuilder emailBuilder;
			MyTableModel myTableModel;
			for(int i =0 ; i < 1000 ; i++) {
				name = faker.name().name();
				firstName= faker.name().firstName();
				emailBuilder  = new StringBuilder(
						firstName.trim().replaceAll(StringUtils.SPACE, StringUtils.EMPTY).toLowerCase())
						.append(".")
						.append(name.trim().replaceAll(StringUtils.SPACE, StringUtils.EMPTY).toLowerCase())
						.append("@")
						.append(faker.company().name().trim().replaceAll(StringUtils.SPACE, "-").toLowerCase())
						.append(".")
						.append(faker.internet().domainName().trim().replaceAll(StringUtils.SPACE, "-").toLowerCase());
				myTableModel = MyTableModel.builder().
						code(faker.code().ean8()).
						name(name + " " + firstName)
						.email(emailBuilder.toString())
						.creationDate(DateFormatUtils.formatSqlDateToString(faker.date().birthday()))
						.address(faker.address().city()).build();
				tableModelList.add(myTableModel);
			}

			myTableRepository.deleteAll()
					.thenMany(myTableRepository.saveAll(Flux.fromIterable(tableModelList)))
					.log()
					.subscribe(entry -> log.info("Inserting {}", entry.toString()));


			Thread.sleep(500);

		};
	}



}
