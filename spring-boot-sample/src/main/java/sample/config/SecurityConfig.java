package sample.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.X509Configurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/content/**")
                .antMatchers("/swagger-ui/index.html")
                .antMatchers("/test/**")
                .antMatchers("/h2-console/**");
    }


    /**
     * Config des urls protégées et publics
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        X509Configurer x509Configurer = new X509Configurer();
        x509Configurer.init(http);

        http.csrf()
                .disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                /*.antMatchers("/").permitAll()
                .antMatchers("/error").permitAll()*/
                .anyRequest().permitAll();

        http.logout()
                .logoutSuccessUrl("/");
    }

}
