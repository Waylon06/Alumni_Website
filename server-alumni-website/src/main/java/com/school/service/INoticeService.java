package com.school.service;

import com.school.models.Notice;

import java.util.List;

public interface INoticeService {

    public int insert(Notice notice);
    public int delNotice(Notice notice);
    public Notice findById(Notice notice);
    public int change(Notice notice);
    public List<Notice> findAll(Notice notice);
}
