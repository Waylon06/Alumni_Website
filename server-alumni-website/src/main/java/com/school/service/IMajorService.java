package com.school.service;

import com.school.models.Major;

import java.util.List;

public interface IMajorService {
    public int delMajor(Major major);
    public int change(Major major);
    public int insert(Major major);
    public Major findMajorById(Major major);
    public List<Major> findAll(Major major);
}
