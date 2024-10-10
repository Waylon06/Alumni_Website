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
@TableName("feedback")
public class Feedback {
    @TableField("fk_id")
    @Excel(name = "反馈编号")
    private Integer fid;
    @TableField("title")
    @Excel(name = "标题")
    private String title;
    @TableField("content")
    @Excel(name = "内容")
    private String content;

    @Override
    public String toString() {
        return "Feedback{" +
                "fid=" + fid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
