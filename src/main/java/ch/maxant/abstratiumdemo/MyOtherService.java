package ch.maxant.abstratiumdemo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyOtherService {

    @Autowired
    Logger log;

    public void executeSomeLogic() {
        log.debug("executing some logic");
    }

}
