package com.school.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.school.mapper.MajorMapper;
import com.school.models.Major;
import com.school.service.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements IMajorService {

    @Autowired
    private final MajorMapper majorMapper;

    public MajorServiceImpl(MajorMapper majorMapper) {
        this.majorMapper = majorMapper;
    }

    @Override
    public int delMajor(Major major) {
        if (major != null) {
            QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
            majorQueryWrapper.eq("major_id", major.getMid());
            return majorMapper.delete(majorQueryWrapper);
        }
        return 0;
    }

    @Override
    public int change(Major major) {
        if (major != null) {
            UpdateWrapper<Major> majorUpdateWrapper = new UpdateWrapper<>();
            majorUpdateWrapper.eq("major_id", major.getMid());
            return majorMapper.update(major, majorUpdateWrapper);
        }
        return 0;
    }

    @Override
    public int insert(Major major) {
        if (major != null) {
            return majorMapper.insert(major);
        }
        return 0;
    }

    @Override
    public Major findMajorById(Major major) {
        if (major == null) {
            return null;
        }
        QueryWrapper<Major> major_id = new QueryWrapper<Major>().eq("major_id", major.getMid());
        return majorMapper.selectOne(major_id);
    }

    @Override
    public List<Major> findAll(Major major) {
        QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(major.getName())) {
            majorQueryWrapper.like("name", major.getName());
        }
        return majorMapper.selectList(majorQueryWrapper);
    }
}
