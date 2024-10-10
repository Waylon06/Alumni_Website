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
@TableName("classes")
public class Classes {
    @TableField("class_id")
    @Excel(name = "班级编号")
    private Integer cid;
    @TableField("class_name")
    @Excel(name = "班级名称")
    private String name;
    @TableField("major_id")
    @Excel(name = "专业编号")
    private Integer mid;

    @Override
    public String toString() {
        return "Classes{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", mid=" + mid +
                '}';
    }
}
