package ch.maxant.abstratiumdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// https://spring.io/guides/gs/securing-web/
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity( // https://www.baeldung.com/spring-security-method-security
        prePostEnabled = false,
        securedEnabled = true,
        jsr250Enabled = false)
public class MySecurityConfiguration {
/*
    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web
                .ignoring().antMatchers("/users/**", "/", "/home");
    }
*/
    /*
        web.authorizeRequests(request ->
            request
                .antMatchers("/", // static - see resources/static/index.html for example
                             "/home", // a thymeleaf endpoint - see MvcConfig
                             "/users/update/all", // see UserController
                             "/users/**" // see UserController
                ).permitAll()

                .antMatchers(HttpMethod.PUT,
                             "/users/**" // see UserController
                ).permitAll()

                .antMatchers(HttpMethod.POST,
                             "/users/**" // see UserController
                ).permitAll()

                .antMatchers("/actuator/*")
                .hasRole("ADMIN")

                .anyRequest().authenticated() // close everything else
        )
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();
    }
*/

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER", "ADMIN")
                        .build();

        // see also JdbcUserDetailsManager
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(request ->
                        request
                                .antMatchers("/", // static - see resources/static/index.html for example
                                        "/home", // a thymeleaf endpoint - see MvcConfig
                                        "/users/update/all", // see UserController
                                        "/users/**" // see UserController
                                ).permitAll()

                                /* not needed if csrf is disabled
                                .antMatchers(HttpMethod.PUT,
                                        "/users/**" // see UserController
                                ).permitAll()

                                .antMatchers(HttpMethod.POST,
                                        "/users/**" // see UserController
                                ).permitAll()
                                 */

                                .antMatchers("/actuator/*")
                                .hasRole("ADMIN")

                                .anyRequest().authenticated() // close everything else
                )
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        // http.requestMatcher(EndpointRequest.toAnyEndpoint());
        // http.authorizeRequests((requests) -> requests.anyRequest().hasRole("ADMIN"));
        // http.httpBasic(withDefaults());
        http.csrf().disable(); // TODO see https://stackoverflow.com/questions/51026694/spring-security-blocks-post-requests-despite-securityconfig -> there is a better way to do this!
        return http.build();
    }
}