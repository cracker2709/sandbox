package sample.config;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@PropertySource("classpath:config/application.yml")
@ComponentScan(basePackages = { "sample.services","sample.web"})
@EntityScan("sample.domain")
public class SampleConfigurationProperties {

}
