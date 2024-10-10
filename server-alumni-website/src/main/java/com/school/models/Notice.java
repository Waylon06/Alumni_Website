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
@TableName("notice")
public class Notice {
    @TableField("notice_id")
    @Excel(name = "公告编号")
    private Integer nid;
    @TableField("title")
    @Excel(name = "标题")
    private String title;
    @TableField("content")
    @Excel(name = "内容")
    private String content;
    @TableField("create_date")
    @Excel(name = "发布时间", width = 30)
    private String createDate;

    @Override
    public String toString() {
        return "Notice{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
