package sample;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"sample.repository" },
						entityManagerFactoryRef = "bddSampleEntityManager",
						transactionManagerRef = "bddSampleTransactionManager")
public class H2Config {

	@Autowired
	private Environment env;

	@Bean("dataSourcebddSample")
	public DataSource dataSourcebddSample() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));

		return dataSource;
	}

	@Bean(name = "bddSampleEntityManager")
	@PersistenceContext(unitName = "persistenceUnitSample")
	public LocalContainerEntityManagerFactoryBean mssPilEntityManager() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSourcebddSample());
		em.setPersistenceUnitName("persistenceUnitSampleTest");
		em.setPackagesToScan(new String[] { "sample.domain" });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean("bddSampleTransactionManager")
	JpaTransactionManager bddSampleTransactionManager(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("spring.hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql",
				env.getProperty("spring.hibernate.show_sql") != null ? env.getProperty("spring.hibernate.show_sql") : "false");
		return hibernateProperties;
	}

}
