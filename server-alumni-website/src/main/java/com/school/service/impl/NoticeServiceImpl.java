package com.school.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.school.mapper.NoticeMapper;
import com.school.models.Notice;
import com.school.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private final NoticeMapper noticeMapper;

    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }


    @Override
    public int insert(Notice notice) {
        if (notice != null) {
            return noticeMapper.insert(notice);
        }
        return 0;
    }

    @Override
    public int delNotice(Notice notice) {
        if (notice != null) {
            QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
            QueryWrapper<Notice> notice_id = noticeQueryWrapper.eq("notice_id", notice.getNid());
            return noticeMapper.delete(notice_id);
        }
        return 0;
    }

    @Override
    public Notice findById(Notice notice) {
        if (notice.getNid() != null) {
            QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
            noticeQueryWrapper.eq("notice_id", notice.getNid());
            return noticeMapper.selectOne(noticeQueryWrapper);
        }
        return null;
    }

    @Override
    public int change(Notice notice) {
        if (notice != null) {
            UpdateWrapper<Notice> noticeUpdateWrapper = new UpdateWrapper<>();
            noticeUpdateWrapper.eq("notice_id", notice.getNid());
            return noticeMapper.update(notice, noticeUpdateWrapper);
        }
        return 0;
    }

    @Override
    public List<Notice> findAll(Notice notice) {
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(notice.getTitle())) {
            noticeQueryWrapper.like("title", notice.getTitle());
        }
        return noticeMapper.selectList(noticeQueryWrapper);
    }
}
