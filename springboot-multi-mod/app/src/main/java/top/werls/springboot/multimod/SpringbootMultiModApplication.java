package top.werls.springboot.multimod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.werls.springboot.multimod.common.Utils;

@SpringBootApplication
@Slf4j
public class SpringbootMultiModApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMultiModApplication.class, args);
        log.info(Utils.demo());
    }
}
