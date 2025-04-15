package com.deark.be.global.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.deark.be.global.exception.FileConvertFailException;
import com.deark.be.global.exception.FileDeleteFailException;
import com.deark.be.global.exception.FileNotImageException;
import com.deark.be.global.exception.FileUploadFailException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.deark.be.global.exception.errorcode.GlobalErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    // 이미지 단 건 업로드
    public String uploadFile(MultipartFile file) {
        validateFile(file);

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();

        // 이미 존재하는지 확인
        if (amazonS3.doesObjectExist(bucket, fileName)) {
            return amazonS3.getUrl(bucket, fileName).toString();
        }

        putImage(fileName, metadata, file);

        // 업로드된 파일의 URL 반환
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    // 이미지 다 건 업로드
    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> fileUrls = new ArrayList<>();
        String fileName = UUID.randomUUID() + "_";
        ObjectMetadata metadata = new ObjectMetadata();

        for (MultipartFile file : files) {
            validateFile(file);

            fileName += file.getOriginalFilename();

            // 이미 존재하는지 확인
            if (amazonS3.doesObjectExist(bucket, fileName)) {
                fileUrls.add(amazonS3.getUrl(bucket, fileName).toString());
            }
            else {
                putImage(fileName, metadata, file);
                fileUrls.add(amazonS3.getUrl(bucket, fileName).toString());
            }
        }

        return fileUrls;
    }

    // s3에 등록
    private void putImage(String fileName, ObjectMetadata metadata, MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata));
        } catch (IOException e) {
            throw new FileUploadFailException(FILE_NOT_UPLOADED);
        }
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileConvertFailException(FILE_CONVERT_FAIL);
        }

        String contentType = file.getContentType();

        if (StringUtils.isEmpty(contentType) || !contentType.startsWith("image/")) {
            throw new FileNotImageException(FILE_NOT_IMAGE);
        }
    }

    public void deleteImageFromS3(String imageAddress){
        String key = getKeyFromImageAddress(imageAddress);

        try{
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, key));
        }catch (Exception e){
            throw new FileDeleteFailException(FILE_DELETE_FAIL);
        }
    }

    private String getKeyFromImageAddress(String imageAddress){
        try{
            URL url = new URL(imageAddress);
            String decodingKey = URLDecoder.decode(url.getPath(), "UTF-8");

            return decodingKey.substring(1); // 맨 앞의 '/' 제거
        } catch (MalformedURLException | UnsupportedEncodingException e){
            throw new FileDeleteFailException(FILE_DELETE_FAIL);
        }
    }
}
