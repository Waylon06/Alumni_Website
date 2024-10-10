package com.school.controllers;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.models.Activity;
import com.school.service.IActivityService;
import com.school.service.IUploadImgService;
import com.school.utils.HttpStatusCode;
import com.school.utils.Respond;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/activity")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    private final IActivityService activityService;
    @Autowired
    private final IUploadImgService uploadImgService;

    public ActivityController(IActivityService activityService, IUploadImgService uploadImgService) {
        this.activityService = activityService;
        this.uploadImgService = uploadImgService;
    }

    @RequestMapping("/list")
    public Respond<PageInfo<Activity>> findAll(Activity activity, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> all = activityService.findAll(activity);
        PageInfo<Activity> pageInfo = new PageInfo<>(all);
        return Respond.success(pageInfo);
    }

    @RequestMapping("/all")
    public Respond<List<Activity>> activateAll(Activity activity) {
        return Respond.success(activityService.findAll(activity));
    }

    @RequestMapping("/add")
    public Respond addActivity(Activity activity) {
        activity.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int insert = activityService.insert(activity);
        if (insert > 0) {
            return Respond.success("增加成功");
        }
        return Respond.fail(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), "增加失败");
    }

    @DeleteMapping("/del")
    public Respond delActivity(Activity activity) {
        int i = activityService.delActivity(activity);
        if (i > 0) {
            return Respond.success("删除成功");
        }
        return Respond.fail(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), "删除失败");
    }

    @RequestMapping("/change")
    public Respond changeActivity(Activity activity) {
        System.out.println(activity);
        int i = activityService.change(activity);
        if (i > 0) {
            return Respond.success("修改成功");
        }
        return Respond.fail(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), "修改失败");
    }


    @RequestMapping("/getActivityById")
    public Respond findById(Activity activity) {
        System.out.println(activity.getAid());
        Activity res = activityService.findById(activity);
        if (res != null) {
            return Respond.success(res);
        }
        return Respond.fail("暂无此id记录");
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response, Activity activity) throws IOException {
        List<Activity> activities = activityService.findAll(activity);
        ExportParams params = new ExportParams("活动列表", "活动列表");
        params.setType(ExcelType.XSSF); // 使用.xlsx格式

        // 导出Excel
        Workbook workbook = ExcelExportUtil.exportExcel(params, Activity.class, activities);

        // 设置响应头和输出流
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=activities.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        workbook.close();
        outputStream.close();
    }

}
