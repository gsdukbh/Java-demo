package top.werls.springbootuploadfile.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.werls.springbootuploadfile.util.MinioUtil;

import java.net.URL;

/**
 * @author leejiawei
 */
@Configuration
public class MinioConfig {
    @Value("${minio.url}")
    private  String url;
    @Value("${minio.username}")
    private  String username;
    @Value("${minio.password}")
    private  String password;
    @Value("${minio.bucketName}")
    private  String bucketName;

    @Bean
    public void minioInit (){
        MinioUtil.minioInit(url,username,password,bucketName);
    }

}
