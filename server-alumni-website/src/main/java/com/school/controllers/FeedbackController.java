package com.school.controllers;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.models.Feedback;
import com.school.models.Major;
import com.school.service.IFeedbackService;
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
@RequestMapping("api/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;

    public FeedbackController(IFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping("/add")
    public Respond addFeedback(Feedback feedback) {
        int i = feedbackService.insert(feedback);
        if (i == 0) {
            return Respond.fail("参数错误");
        }
        return Respond.success("添加成功");
    }

    @RequestMapping("/del")
    public Respond delFeedback(Feedback feedback) {
        int i = feedbackService.delFeedback(feedback);
        if (i == 0) {
            return Respond.fail("删除失败");
        }
        return Respond.success("删除成功");
    }

    @RequestMapping("/list")
    public Respond<PageInfo<Feedback>> findAll(Feedback feedback, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Respond.success(new PageInfo<Feedback>(feedbackService.findAll(feedback)));
    }

    @RequestMapping("/all")
    public Respond<List<Feedback>> feedbackAll(Feedback feedback) {
        return Respond.success(feedbackService.findAll(feedback));
    }
    @RequestMapping("change")
    public Respond changeFeedback(Feedback feedback) {
        int i = feedbackService.change(feedback);
        if (i == 0) {
            return Respond.fail("修改失败");
        }
        return Respond.success("修改成功");
    }
    @RequestMapping("/findById")
    public Respond findById(Feedback feedback) {
        return Respond.success(feedbackService.findById(feedback));
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response, Feedback feedback) throws IOException {
        List<Feedback> feedbacks = feedbackService.findAll(feedback);
        ExportParams params = new ExportParams("反馈列表", "反馈列表");
        params.setType(ExcelType.XSSF); // 使用.xlsx格式

        // 导出Excel
        Workbook workbook = ExcelExportUtil.exportExcel(params, Feedback.class, feedbacks);

        // 设置响应头和输出流
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=feedbacks.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        workbook.close();
        outputStream.close();
    }
}
