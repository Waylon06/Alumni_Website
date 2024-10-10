package com.school.service;

import com.school.models.Feedback;

import java.util.List;

public interface IFeedbackService {
    public int insert(Feedback feedback);
    public int delFeedback(Feedback feedback);
    public List<Feedback> findAll(Feedback feedback);

    int change(Feedback feedback);
    Feedback findById(Feedback feedback);
}
