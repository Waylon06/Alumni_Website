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
@TableName("student")
public class Student {
    @TableField("stu_id")
    @Excel(name = "学生id")
    private Integer sid;
    @Excel(name = "名字", width = 30)
    @TableField("name")
    private String name;
    @TableField("sex")
    @Excel(name = "性别", width = 30)
    private String sex;
    @TableField("class_id")
    @Excel(name = "班级id", width = 30)
    private Integer classId;
    @TableField("major_id")
    @Excel(name = "专业id", width = 30)
    private Integer majorId;
    @TableField("grade")
    @Excel(name = "年级", width = 30)
    private String grade;
    @TableField("user_id")
    @Excel(name = "用户id", width = 30)
    private Integer userId;

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", classId='" + classId + '\'' +
                ", majorId='" + majorId + '\'' +
                ", grade='" + grade + '\'' +
                ", userId=" + userId +
                '}';
    }
}
