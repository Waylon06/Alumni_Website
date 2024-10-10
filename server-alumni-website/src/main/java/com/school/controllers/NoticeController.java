package com.school.controllers;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.models.Activity;
import com.school.models.Notice;
import com.school.service.INoticeService;
import com.school.utils.Respond;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/notice")
@CrossOrigin(origins = "*")
public class NoticeController {

    @Autowired
    private final INoticeService noticeService;

    public NoticeController(INoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @RequestMapping("/all")
    public Respond<List<Notice>> notifyAll(Notice notice) {
        return Respond.success(noticeService.findAll(notice));
    }
    @RequestMapping("/add")
    public Respond addNotice(Notice notice) {
        if (notice.getCreateDate() == null) {
            notice.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        int i = noticeService.insert(notice);
        if (i == 0) {
            return Respond.fail();
        }
        return Respond.success();
    }

    @RequestMapping("/list")
    public Respond<PageInfo<Notice>> findAll(Notice notice, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Respond.success(new PageInfo<Notice>(noticeService.findAll(notice)));
    }


    @RequestMapping("/del")
    public Respond delNotice(Notice notice) {
        int i = noticeService.delNotice(notice);
        if (i == 0) {
            return Respond.fail();
        }
        return Respond.success();
    }

    @RequestMapping("/findById")
    public Respond findById(Notice notice) {
        Notice res = noticeService.findById(notice);
        if (res == null) {
            return Respond.fail();
        }
        return Respond.success(res);
    }

    @RequestMapping("/change")
    public Respond changeNotice(Notice notice) {
        int i = noticeService.change(notice);
        if (i == 0) {
            return Respond.fail();
        }
        return Respond.success();
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response, Notice notice) throws IOException {
        List<Notice> notices = noticeService.findAll(notice);
        ExportParams params = new ExportParams("通知列表", "通知列表");
        params.setType(ExcelType.XSSF); // 使用.xlsx格式

        // 导出Excel
        Workbook workbook = ExcelExportUtil.exportExcel(params, Notice.class, notices);

        // 设置响应头和输出流
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=notices.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        workbook.close();
        outputStream.close();
    }
}
