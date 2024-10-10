package com.school.controllers;

import com.school.service.IUploadImgService;
import com.school.utils.Respond;
import com.school.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/uploadImg")
@CrossOrigin(origins = "*")
public class UploadImgController {

    @Autowired
    private final IUploadImgService uploadImgService;

    public UploadImgController(IUploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }


    @PostMapping("/upload")
    public Respond uploadImg(@RequestParam("file") MultipartFile imgFile) throws IOException {
        if (imgFile == null || imgFile.isEmpty()) {
            return Respond.fail("文件为空");
        }
//        本地
//        String s = uploadImgService.uploadImg(imgFile);
//        阿里云OSS
        String s = UploadUtil.uploadImg(imgFile);
        return Respond.success("上传成功",s);
    }
}
