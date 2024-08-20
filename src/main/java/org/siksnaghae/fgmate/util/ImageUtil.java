package org.siksnaghae.fgmate.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.siksnaghae.fgmate.common.response.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

import static org.siksnaghae.fgmate.common.response.BaseResponseStatus.IMAGE_UPLOAD_FAIL;

public class ImageUtil {
    public static String saveImg(MultipartFile file, AmazonS3Client amazonS3Client, String bucketName) throws BaseException {
        try {

            String fileName = file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucketName,fileName, file.getInputStream(), metadata);
            URL fileUrl = amazonS3Client.getUrl(bucketName, fileName);

            return fileUrl.toString();
        } catch (Exception exception) {
            throw new BaseException(IMAGE_UPLOAD_FAIL);
        }
    }
}
