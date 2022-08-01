package ch.maxant.abstratiumdemo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Factory {

    @Autowired
    Logger log;

    @Bean
    public MyService getMyController() {
        log.info("creating MyController");
        return new MyService();
    }

}
