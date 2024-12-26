package io.hhplus.lectureapply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LectureApplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LectureApplyApplication.class, args);
    }

}
