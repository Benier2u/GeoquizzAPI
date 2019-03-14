package org.lpro.geoquizzplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.lpro.geoquizzplayer")
public class GeoQuizzServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeoQuizzServiceApplication.class, args);
    }
}
