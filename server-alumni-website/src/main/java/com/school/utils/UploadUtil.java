package com.school.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
    public static final String ALI_DOMAIN = "https://waylon-personal-bucket.oss-cn-chengdu.aliyuncs.com/";
    public static String uploadImg(MultipartFile imgFile) throws IOException {
        String originalFilename = imgFile.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;
//        地域节点
        String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
        String accessKeyId = "LTAI5tEfPZ6CM8hnp45qpPwn";
        String accessKeySecret = "MSnE4hzA1B9QMInTBvcE396c1KWN7j";
        OSS build = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        build.putObject("waylon-personal-bucket", fileName, imgFile.getInputStream());
        build.shutdown();
        return ALI_DOMAIN + fileName;
    }
}
