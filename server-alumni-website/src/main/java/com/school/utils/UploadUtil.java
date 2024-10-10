package com.school.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
    public static final String ALI_DOMAIN = "";
    public static String uploadImg(MultipartFile imgFile) throws IOException {
        String originalFilename = imgFile.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;
//        地域节点
        String endpoint = "";
        // String accessKeyId = "";
        // String accessKeySecret = "";
        OSS build = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        build.putObject("", fileName, imgFile.getInputStream());
        build.shutdown();
        return ALI_DOMAIN + fileName;
    }
}
