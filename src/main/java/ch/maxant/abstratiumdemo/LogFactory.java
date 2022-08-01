package ch.maxant.abstratiumdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;
import java.util.Optional;

@Configuration
public class LogFactory {

    // empty scope implies SCOPE_SINGLETON.
    // see also https://www.baeldung.com/spring-bean-scopes
    // ConfigurableBeanFactory.SCOPE_PROTOTYPE --> will return a different instance every time it is requested from the container
    // ConfigurableBeanFactory.SCOPE_SINGLETON
    // org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST
    // org.springframework.web.context.WebApplicationContext.SCOPE_SESSION
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(final InjectionPoint ip) {
        // https://medium.com/simars/inject-loggers-in-spring-java-or-kotlin-87162d02d068
        // https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/boot-features-logging.html
        return LoggerFactory.getLogger(Optional.ofNullable(ip.getMethodParameter())
                .<Class>map(MethodParameter::getContainingClass)
                .orElseGet( () ->
                        Optional.ofNullable(ip.getField())
                                .map(Field::getDeclaringClass)
                                .orElseThrow (IllegalArgumentException::new)
                )
        );
    }


}
