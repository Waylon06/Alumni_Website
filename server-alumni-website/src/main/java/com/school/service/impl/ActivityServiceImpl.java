package com.school.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.school.mapper.ActivityMapper;
import com.school.models.Activity;
import com.school.service.IActivityService;
import lombok.extern.log4j.Log4j;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private final ActivityMapper activityMapper;

    public ActivityServiceImpl(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    @Override
    public int insert(Activity activity) {
        if (activity == null) {
            return 0;
        }
        return activityMapper.insert(activity);
    }

    @Override
    public int delActivity(Activity activity) {
        if (activity == null) {
            return 0;
        }
        QueryWrapper<Activity> activityQueryWrapper = new QueryWrapper<>();
        activityQueryWrapper.eq("activity_id", activity.getAid());
        return activityMapper.delete(activityQueryWrapper);

    }

    @Override
    public int change(Activity activity) {
        if (activity == null) {
            return 0;
        }
        UpdateWrapper<Activity> activityUpdateWrapper = new UpdateWrapper<>();
        activityUpdateWrapper.eq("activity_id", activity.getAid());
        return activityMapper.update(activity, activityUpdateWrapper);
    }

    @Override
    public List<Activity> findAll(Activity activity) {
        QueryWrapper<Activity> activityQueryWrapper = new QueryWrapper<>();
        if (activity.getUserId() != null) {
            activityQueryWrapper.eq("user_id", activity.getUserId());
        }
        if(!StringUtils.isEmpty(activity.getTitle())) {
            activityQueryWrapper.like("title", activity.getTitle());
        }
        if(!StringUtils.isEmpty(activity.getPlace())) {
            activityQueryWrapper.like("place", activity.getPlace());
        }
        List<Activity> activityList = activityMapper.selectList(activityQueryWrapper);
        return activityList;
    }

    @Override
    public Activity findById(Activity activity) {
        QueryWrapper<Activity> activityQueryWrapper = new QueryWrapper<>();
        activityQueryWrapper.eq("activity_id", activity.getAid());
        Activity res = activityMapper.selectOne(activityQueryWrapper);
        if (res == null){
            return null;
        }
        return res;
    }


}
