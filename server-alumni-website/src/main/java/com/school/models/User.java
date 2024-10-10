package com.school.models;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    /**
     * 用户名
     */
//    @NotBlank(message = "用户名不能为空")
//    @Length(min = 6, max = 15, message = "用户名应该在6-15位")
    @TableField("username")
    @Excel(name = "用户名")
    private String username;

    /**
     * 密码
     */
//    @NotBlank(message = "密码不能为空")
//    @Length(min = 8, max = 20, message = "用户名应该在8-20位")
    @TableField("password")
    @Excel(name = "密码")
    private String password;

    /**
     * 邮箱
     */
//    @Email(message = "邮箱格式不正确")
//    @NotBlank(message = "邮箱不能为空")
    @TableField("email")
    @Excel(name = "邮箱")
    private String email;

    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    @TableField("user_id")
    @Excel(name = "用户id")
    private Integer userId;


    @TableField("avatar")
    @Excel(name = "头像")
    private String avatar;

    @TableField("admin")
    @Excel(name = "权限")
    private Integer admin;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }
}
