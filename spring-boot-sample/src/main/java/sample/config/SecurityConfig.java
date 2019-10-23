package sample.config;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Setter(onMethod = @__({@Autowired}))
@Log4j2
public class SecurityConfig {

    private static final String ADMIN_PATH = "/**/admin/**";
    private static final String ALL_PATHS = "/**/**";



    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {

        http
                .csrf().disable()
                .authorizeExchange()
                .matchers(pathMatchers(HttpMethod.OPTIONS, ADMIN_PATH)).permitAll()
                .pathMatchers(ADMIN_PATH).authenticated()
                .anyExchange().permitAll();

        return http.build();
    }


}
