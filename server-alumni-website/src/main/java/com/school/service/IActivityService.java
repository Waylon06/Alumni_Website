package com.school.service;

import com.school.models.Activity;

import java.util.List;

public interface IActivityService {
    public int insert(Activity activity);
    public int delActivity(Activity activity);
    public int change(Activity activity);
    public List<Activity> findAll(Activity activity);
    public Activity findById(Activity activity);
}
