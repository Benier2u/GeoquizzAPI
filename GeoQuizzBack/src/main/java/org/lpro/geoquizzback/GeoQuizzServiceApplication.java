package org.lpro.geoquizzback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients("org.lpro.geoquizzback")
@EnableSwagger2
public class GeoQuizzServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeoQuizzServiceApplication.class, args);
    }
}
