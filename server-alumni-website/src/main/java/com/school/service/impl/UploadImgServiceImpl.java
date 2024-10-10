package com.school.service.impl;

import com.school.mapper.ActivityMapper;
import com.school.service.IUploadImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadImgServiceImpl implements IUploadImgService {

    @Override
    public String uploadImg(MultipartFile imgFile) {
        if (imgFile.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String originalFilename = imgFile.getOriginalFilename();
        String ext = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        String save_pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                "\\src\\main\\resources\\static\\images\\";
        String pre = "localhost:8089\\images\\";
//        String pre = "C:\\Users\\Waylon\\Desktop\\client-alumni-website\\src\\assets\\images\\";
        String path = pre + fileName;
        try {
            imgFile.transferTo(new File(save_pre + fileName));
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
