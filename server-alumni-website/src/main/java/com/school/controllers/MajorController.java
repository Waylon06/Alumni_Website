package com.school.controllers;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.models.Major;
import com.school.models.Notice;
import com.school.service.IMajorService;
import com.school.utils.Respond;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/major")
@CrossOrigin(origins = "*")
public class MajorController {

    @Autowired
    private final IMajorService majorService;

    public MajorController(IMajorService majorService) {
        this.majorService = majorService;
    }

    @RequestMapping("/findById")
    public Respond findById(Major major) {
        Major res = majorService.findMajorById(major);
        if (res == null) {
            return Respond.fail();
        }
        return Respond.success(res);
    }

    @RequestMapping("/del")
    public Respond delMajor(Major major) {
        int i = majorService.delMajor(major);
        if (i == 0) {
            return Respond.fail();
        }
        return Respond.success();
    }

    @RequestMapping("/add")
    public Respond addMajor(Major major) {
        int i = majorService.insert(major);
        if (i == 0) {
            return Respond.fail();
        }
        return Respond.success();
    }

    @RequestMapping("/change")
    public Respond changeMajor(Major major) {
        int i = majorService.change(major);
        if (i == 0) {
            return Respond.fail();
        }
        return Respond.success();
    }

    @RequestMapping("/list")
    public Respond<PageInfo<Major>> findAll(Major major, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Respond.success(new PageInfo<Major>(majorService.findAll(major)));
    }

    @RequestMapping("/all")
    public Respond<List<Major>> majorAll(Major major) {
        return Respond.success(majorService.findAll(major));
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response, Major major) throws IOException {
        List<Major> majors = majorService.findAll(major);
        ExportParams params = new ExportParams("专业列表", "专业列表");
        params.setType(ExcelType.XSSF); // 使用.xlsx格式

        // 导出Excel
        Workbook workbook = ExcelExportUtil.exportExcel(params, Major.class, majors);

        // 设置响应头和输出流
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=majors.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        workbook.close();
        outputStream.close();
    }
}
