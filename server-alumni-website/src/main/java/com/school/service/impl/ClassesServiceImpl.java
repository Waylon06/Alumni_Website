package com.school.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.school.mapper.ClassesMapper;
import com.school.models.Classes;
import com.school.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements IClassesService {
    @Autowired
    private final ClassesMapper classesMapper;

    public ClassesServiceImpl(ClassesMapper classesMapper) {
        this.classesMapper = classesMapper;
    }

    @Override
    public int delClasses(Classes classes) {
        if (classes == null) {
            return 0;
        }
        QueryWrapper<Classes> classesQueryWrapper = new QueryWrapper<>();
        classesQueryWrapper.eq("class_id", classes.getCid());
        return classesMapper.delete(classesQueryWrapper);
    }

    @Override
    public int change(Classes classes) {
        if (classes != null) {
            UpdateWrapper<Classes> classesUpdateWrapper = new UpdateWrapper<>();
            classesUpdateWrapper.eq("class_id", classes.getCid());
            return classesMapper.update(classes, classesUpdateWrapper);
        }
        return 0;
    }

    @Override
    public int insert(Classes classes) {
        if (classes != null) {
            return classesMapper.insert(classes);
        }
        return 0;
    }

    @Override
    public Classes findClassesById(Classes classes) {
        if (classes == null) {
            return null;
        }
        QueryWrapper<Classes> classesQueryWrapper = new QueryWrapper<>();
        classesQueryWrapper.eq("class_id", classes.getCid());
        return classesMapper.selectOne(classesQueryWrapper);
    }

    @Override
    public List<Classes> findAll(Classes classes) {
        QueryWrapper<Classes> classesQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(classes.getName())){
            classesQueryWrapper.like("class_name", classes.getName());
        }
        if(classes.getMid() != null) {
            classesQueryWrapper.eq("major_id", classes.getMid());
        }
        return classesMapper.selectList(classesQueryWrapper);
    }
}
