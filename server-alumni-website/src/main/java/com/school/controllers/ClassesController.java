package com.school.controllers;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.models.Activity;
import com.school.models.Classes;
import com.school.models.Major;
import com.school.models.Student;
import com.school.service.IClassesService;
import com.school.utils.Respond;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/classes")
@CrossOrigin(origins = "*")
public class ClassesController {

    @Autowired
    private final IClassesService classesService;

    public ClassesController(IClassesService classesService) {
        this.classesService = classesService;
    }

    @RequestMapping("/add")
    public Respond addClass(Classes classes) {
        if (classesService.insert(classes) == 0) {
            return Respond.fail("添加失败");
        }
        return Respond.success("添加成功");
    }

    @RequestMapping("/del")
    public Respond delClass(Classes classes) {
        if (classesService.delClasses(classes) == 0) {
            return Respond.fail("删除失败");
        }
        return Respond.success("删除成功");
    }

    @RequestMapping("/change")
    public Respond changeClass(Classes classes) {
        if (classesService.change(classes) == 0) {
            return Respond.fail("修改失败");
        }
        return Respond.success("修改成功");
    }

    @RequestMapping("/findById")
    public Respond findById(Classes classes) {
        Classes classes1 = classesService.findClassesById(classes);
        if (classes1 == null) {
            return Respond.fail("查询失败");
        }
        return Respond.success(classes1);
    }

    @RequestMapping("/list")
    public Respond<PageInfo<Classes>> findAll(Classes classes, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Classes> all = classesService.findAll(classes);
        PageInfo<Classes> pageInfo = new PageInfo<>(all);
        if (classes == null) {
            return Respond.fail("查询失败");
        }
        return Respond.success(pageInfo);
    }

    @RequestMapping("/all")
    public Respond<List<Classes>> listAll(Classes classes) {
        if (classes == null) {
            return Respond.fail("查询失败");
        }
        List<Classes> all = classesService.findAll(classes);
        return Respond.success(all);
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response, Classes classes) throws IOException {
        List<Classes> classes1 = classesService.findAll(classes);
        ExportParams params = new ExportParams("班级列表", "班级列表");
        params.setType(ExcelType.XSSF); // 使用.xlsx格式

        // 导出Excel
        Workbook workbook = ExcelExportUtil.exportExcel(params, Classes.class, classes1);

        // 设置响应头和输出流
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=classes.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        workbook.close();
        outputStream.close();
    }
}
