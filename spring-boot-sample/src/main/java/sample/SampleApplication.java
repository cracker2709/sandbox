package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication
@EnableWebFlux
@ComponentScan(basePackages = { "sample", "sample.services", "sample.manager.services", "sample.security.service","sample.web", "sample.exception.advice", "sample.services.filters"})
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(sample.SampleApplication.class, args);
	}

	// IF NEEDED DISABLE DEFAULT ERROR FILTER PAGE
	/*@Bean
	public ErrorPageFilter errorPageFilter() {
		return new ErrorPageFilter();
	}

	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}*/
}
