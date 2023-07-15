package kbl.test.hdj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HdjApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdjApplication.class, args);
    }

}
