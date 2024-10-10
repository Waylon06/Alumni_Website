package com.school.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadImgService {
    public String uploadImg(MultipartFile imgFile);
}
