package com.school.service;

import com.school.models.Classes;

import java.util.List;

public interface IClassesService {
    public int delClasses(Classes classes);
    public int change(Classes classes);
    public int insert(Classes classes);
    public Classes findClassesById(Classes classes);
    public List<Classes> findAll(Classes classes);
}
