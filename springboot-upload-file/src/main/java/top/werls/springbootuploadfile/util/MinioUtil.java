package top.werls.springbootuploadfile.util;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author leejiawei
 */


public class MinioUtil {

    private static String url;
    private static String username;
    private static String password;
    private static String bucketName;

    private static MinioClient minioClient = null;

    /**
     * @param inUrl        地址
     * @param inUsername   用户
     * @param inPassword   密码
     * @param inBucketName 对象桶
     */
    public static void minioInit(String inUrl, String inUsername, String inPassword, String inBucketName) {
        url = inUrl;
        username = inUsername;
        password = inPassword;
        bucketName = inBucketName;

        minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(username, password)
                .build();
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());

            if (isExist) {
                System.out.println(bucketName + "已经存在");
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void fileUpload(MultipartFile file) {
//        System.out.println(file.getName());
        try {

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Iterable<Result<Item>> listObjects() {
        return minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName)
                .build());
    }
}
