package top.werls.springbootuploadfile.util;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author leejiawei
 */

public class MinioUtil {

    private static String url;
    private static String username;
    private static String password;
    private static String bucketName;

    private static MinioClient minioClient = null;

    public static void init(String url, String username, String password, String bucketName) {
        

    }


    public static void fileUpload(MultipartFile file) {


    }
}
