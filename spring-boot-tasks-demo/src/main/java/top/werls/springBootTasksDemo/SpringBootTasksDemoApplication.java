package top.werls.springBootTasksDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author leejiawei
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootTasksDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTasksDemoApplication.class, args);
    }

}
