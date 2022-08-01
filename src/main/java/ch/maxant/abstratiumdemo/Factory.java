package ch.maxant.abstratiumdemo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Factory {

    @Autowired
    Logger log;

    @Value( "${spring.datasource.url}" ) // default value can be appended with a colon
    private String datasourceUrl;

    // bean vs component, is like producer vs jesbean - https://stackoverflow.com/questions/10604298/spring-component-versus-bean
    @Bean
    public MyService getMyService() {
        log.info("creating MyService with datasource {}", datasourceUrl);
        return new MyService();
    }

}
