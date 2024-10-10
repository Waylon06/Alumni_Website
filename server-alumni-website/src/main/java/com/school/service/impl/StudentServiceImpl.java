package com.school.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.school.mapper.StudentMapper;
import com.school.models.Student;
import com.school.service.IStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public int insert(Student student) {
        if (student != null) {
            return studentMapper.insert(student);
        }
        return 0;
    }

    @Override
    public int delStudent(Student student) {
        if (student != null) {
            QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
            studentQueryWrapper.eq("stu_id", student.getSid());
            return studentMapper.delete(studentQueryWrapper);
        }
        return 0;
    }

    @Override
    public Student findById(Student student) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        if (student.getUserId() == null) {
            studentQueryWrapper.eq("stu_id", student.getSid());
        }
        if (student.getSid() == null) {
            studentQueryWrapper.eq("user_id", student.getUserId());
        }
        Student student1 = studentMapper.selectOne(studentQueryWrapper);
        if (student1 != null) {
            return student1;
        }
        return null;
    }

    @Override
    public int change(Student student) {
        if (student != null) {
            UpdateWrapper<Student> studentUpdateWrapper = new UpdateWrapper<>();
            studentUpdateWrapper.eq("stu_id", student.getSid());
            return studentMapper.update(student, studentUpdateWrapper);
        }
        return 0;
    }

    @Override
    public List<Student> findAll(Student student) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(student.getName())) {
            studentQueryWrapper.like("name", student.getName());
        }
        if (!StringUtils.isEmpty(student.getGrade())) {
            studentQueryWrapper.like("grade", student.getGrade());
        }
        if (student.getClassId() != null) {
            studentQueryWrapper.eq("class_id", student.getClassId());
        }
        if(student.getMajorId() != null) {
            studentQueryWrapper.eq("major_id", student.getMajorId());
        }
        if (student.getUserId() != null) {
            studentQueryWrapper.eq("user_id", student.getUserId());
        }
        List<Student> students = studentMapper.selectList(studentQueryWrapper);
        return students;
    }
}
