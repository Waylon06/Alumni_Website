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
@TableName("major")
public class Major {
    @TableField("major_id")
    @Excel(name = "专业编号")
    private Integer mid;
    @TableField("name")
    @Excel(name = "专业名称", width = 30)
    private String name;

    @Override
    public String toString() {
        return "Major{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                '}';
    }
}
