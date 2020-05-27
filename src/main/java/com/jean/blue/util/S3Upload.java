package com.jean.blue.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@NoArgsConstructor
public class S3Upload {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(MultipartFile file, String dirName) throws IOException {

        String originFilename = file.getOriginalFilename();
        String originName = originFilename.substring(0, originFilename.lastIndexOf("."));
        String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());

        String fileName = dirName +  "/" +  changeFilename(originName, extName);

        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        String uploadImageUrl = s3Client.getUrl(bucket, fileName).toString();
        return uploadImageUrl;
    }

    private String changeFilename(String originName, String extName){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.KOREA);
        return originName + "_" + dateTimeFormatter.format(localDateTime) + extName;

    }


    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            // log.info("파일이 삭제되었습니다.");
        } else {
            // log.info("파일이 삭제되지 못했습니다.");
        }
    }
}