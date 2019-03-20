package org.lpro.geoquizzmobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients("org.lpro.geoquizzmobile")
public class GeoQuizzServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeoQuizzServiceApplication.class, args);
    }
}
