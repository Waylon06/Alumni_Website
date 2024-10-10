package com.school.controllers;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.models.Activity;
import com.school.models.Student;
import com.school.service.IStudentService;
import com.school.utils.HttpStatusCode;
import com.school.utils.Respond;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/add")
    public Respond addStudent(Student student) {
        if(student == null) {
            return Respond.fail(HttpStatusCode.BAD_REQUEST.getCode(), "参数错误");
        }
        int i = studentService.insert(student);
        if (i == 0) {
            return Respond.fail();
        }
        return Respond.success();
    }

    @DeleteMapping("/del")
    public Respond deleteStudent(Student student) {
        int i = studentService.delStudent(student);
        if (i == 0) {
            return Respond.fail();
        }
        return Respond.success();
    }

    @RequestMapping("/findById")
    public Respond findById(Student student) {
        if (Objects.isNull(student)) {
            return Respond.fail(HttpStatusCode.BAD_REQUEST.getCode(), "参数错误");
        }
        return Respond.success(studentService.findById(student));
    }

    @PutMapping("/change")
    public Respond changeStudent(Student student) {
        return Respond.success(studentService.change(student));
    }

    @RequestMapping("/list")
    public Respond<PageInfo<Student>> findAll(Student student, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return Respond.success(new PageInfo<Student>(studentService.findAll(student)));
    }

    @RequestMapping("/all")
    public Respond<List<Student>> studentAll(Student student) {
        return Respond.success(studentService.findAll(student));
    }
    @RequestMapping("/export")
    public void export(HttpServletResponse response, Student student) throws IOException {
        List<Student> students = studentService.findAll(student);
        ExportParams params = new ExportParams("学生列表", "学生列表");
        params.setType(ExcelType.XSSF); // 使用.xlsx格式

        // 导出Excel
        Workbook workbook = ExcelExportUtil.exportExcel(params, Student.class, students);

        // 设置响应头和输出流
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        workbook.close();
        outputStream.close();
    }
}
