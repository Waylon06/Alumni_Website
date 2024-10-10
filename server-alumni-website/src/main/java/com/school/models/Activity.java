package com.school.models;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("activity")
public class Activity {
    @TableField("activity_id")
    @Excel(name = "活动编号")
    private Integer aid;
    @TableField("title")
    @Excel(name = "标题")
    private String title;
    @TableField("content")
    @Excel(name = "内容")
    private String content;
    @TableField("start_time")
    @Excel(name = "开始时间", width = 30)
    private String startTime;
    //    @TableField("end_time")
//    private String endTime;
    @TableField("pic")
    @Excel(name = "照片链接")
    private String pic;
    @TableField("place")
    @Excel(name = "地点")
    private String place;
    @TableField("user_id")
    @Excel(name = "发布者")
    private Integer userId;

    @Override
    public String toString() {
        return "Activity{" +
                "aid=" + aid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startTime='" + startTime + '\'' +
                ", pic='" + pic + '\'' +
                ", place='" + place + '\'' +
                ", userId=" + userId +
                '}';
    }
}
