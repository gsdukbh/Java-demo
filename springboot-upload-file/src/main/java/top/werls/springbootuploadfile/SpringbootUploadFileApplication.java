package top.werls.springbootuploadfile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.werls.springbootuploadfile.app.Service.StorageService;

@SpringBootApplication
//@EnableConfigurationProperties(StorageProperties.class)
public class SpringbootUploadFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUploadFileApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
