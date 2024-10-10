package com.school.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.school.mapper.FeedbackMapper;
import com.school.models.Feedback;
import com.school.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public int insert(Feedback feedback) {
        int i = feedbackMapper.insert(feedback);
        return i > 0 ? 1 : 0;
    }

    @Override
    public int delFeedback(Feedback feedback) {
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fk_id", feedback.getFid());
        int i = feedbackMapper.delete(queryWrapper);
        return i > 0 ? 1 : 0;
    }

    @Override
    public List<Feedback> findAll(Feedback feedback) {
        QueryWrapper<Feedback> feedbackQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(feedback.getTitle())){
            feedbackQueryWrapper.like("title", feedback.getTitle());
        }
        return feedbackMapper.selectList(feedbackQueryWrapper);
    }

    @Override
    public int change(Feedback feedback) {
        UpdateWrapper<Feedback> feedbackUpdateWrapper = new UpdateWrapper<>();
        feedbackUpdateWrapper.eq("fk_id", feedback.getFid());
        int update = feedbackMapper.update(feedback, feedbackUpdateWrapper);
        if (update > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public Feedback findById(Feedback feedback) {
        QueryWrapper<Feedback> fk_id = new QueryWrapper<Feedback>().eq("fk_id", feedback.getFid());
        return feedbackMapper.selectOne(fk_id);
    }
}
