package ch.maxant.abstratiumdemo;

import java.util.concurrent.ThreadLocalRandom;

// this bean is created by the Factory class
public class MyService {

    public long getRandomLong() {
        return ThreadLocalRandom.current().nextLong();
    }

}
