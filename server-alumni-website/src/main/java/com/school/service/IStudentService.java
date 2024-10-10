package com.school.service;

import com.school.models.Student;

import java.util.List;

public interface IStudentService {
    int insert(Student student);

    int delStudent(Student student);

    Student findById(Student student);

    int change(Student student);

    List<Student> findAll(Student student);
}
