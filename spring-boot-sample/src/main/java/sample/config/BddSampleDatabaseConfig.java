package sample.config;
import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.Style;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.ClassUtils;

@Configuration
@EnableJpaRepositories(basePackages = {"sample.repository" }, entityManagerFactoryRef = "bddSampleEntityManager",transactionManagerRef="bddSampleTransactionManager")
@AutoConfigureOrder
public class BddSampleDatabaseConfig {

    @Autowired
    private Environment env;

    /**
     *
     * @return
     */
    @Bean("dataSourcebddSample")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSourcebddSample() {
        final DriverManagerDataSource dataSourcebddSample = new DriverManagerDataSource();
        dataSourcebddSample.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSourcebddSample.setUrl(env.getProperty("spring.datasource.url"));
        dataSourcebddSample.setUsername(env.getProperty("spring.datasource.username"));
        dataSourcebddSample.setPassword(env.getProperty("spring.datasource.password"));
        return dataSourcebddSample;
    }

    /**
     *
     * @return
     */
    @Bean(name = "bddSampleEntityManager")
    @PersistenceContext(unitName = "persistenceUnitbddSample")
    public LocalContainerEntityManagerFactoryBean bddSampleEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourcebddSample());
        em.setPersistenceUnitName("persistenceUnitbddSample");
        em.setPackagesToScan("sample.domain");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean(name="bddSampleTransactionManager")
    JpaTransactionManager bddSampleTransactionManager(
            @Qualifier("bddSampleEntityManager") final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @ConfigurationProperties(prefix = "spring.hibernate")
    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        if(env != null) {
            hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.hibernate.hbm2ddl.auto") != null ? env.getProperty("spring.hibernate.hbm2ddl.auto") : "validate");
            hibernateProperties.setProperty("hibernate.dialect", env.getProperty("spring.hibernate.dialect") != null ? env.getProperty("spring.hibernate.dialect") : "org.hibernate.dialect.MySQL5InnoDBDialect");
            hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("spring.hibernate.show_sql") != null ? env.getProperty("spring.hibernate.show_sql") : "false");
        }
        return hibernateProperties;
    }

    /**
     *
     * @author a204043
     *
     */
    static class HibernateCondition extends SpringBootCondition {

        private static final String[] CLASS_NAMES = { "org.hibernate.ejb.HibernateEntityManager",
                "org.hibernate.jpa.HibernateEntityManager" };

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            ConditionMessage.Builder message = ConditionMessage.forCondition("Hibernate");

            return Arrays.stream(CLASS_NAMES)
                    .filter(className -> ClassUtils.isPresent(className, context.getClassLoader()))
                    .map(className -> ConditionOutcome.match(message.found("class").items(Style.NORMAL, className)))
                    .findAny().orElseGet(() -> ConditionOutcome.noMatch(
                            message.didNotFind("class", "classes").items(Style.NORMAL, Arrays.asList(CLASS_NAMES))));
        }

    }
}
