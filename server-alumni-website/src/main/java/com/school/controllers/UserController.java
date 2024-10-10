package com.school.controllers;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.models.Student;
import com.school.models.User;
import com.school.service.IUserService;
import com.school.utils.HttpStatusCode;
import com.school.utils.Respond;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author Waylon
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Respond verifyUser(@RequestBody User user) {
        if (Objects.isNull(user)) {
            return Respond.fail(HttpStatusCode.BAD_REQUEST.getCode(), "用户名或密码不能为空");
        }

        String uname = user.getUsername();
        String psw = user.getPassword();
        if ("".equals(uname) || "".equals(psw)) {
            return Respond.fail(HttpStatusCode.BAD_REQUEST.getCode(), "用户名或密码不能为空");
        } else {
            User userData = userService.verifyUser(user);
            if (userData != null) {
                return Respond.success("登1录成功", userData);
            } else {
                return Respond.fail(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), "用户不存在/密码错误");
            }
        }
    }

    @PostMapping("/register")
    public Respond register(@RequestBody User user) {
        String uname = user.getUsername();
        String psw = user.getPassword();
        String email = user.getEmail();
        if ("".equals(uname) || "".equals(psw) || "".equals(email)) {
            return Respond.fail(HttpStatusCode.BAD_REQUEST.getCode(), "用户名或密码、邮箱不能为空");
        } else {
            User isExist = userService.verifyUser(user);
            System.out.println(isExist);
            if (isExist == null) {
                int register = userService.register(user);
                if (register > 0) {
                    User userData = userService.verifyUser(user);
                    return Respond.success("注册成功");
                } else {
                    return Respond.fail(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), "注册失败");
                }
            } else {
                return Respond.fail(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), "注册失败");
            }
        }
    }

    @PostMapping("/change")
        public Respond changeUser(@RequestBody User user) {
        if (Objects.isNull(user)) {
            return Respond.fail(HttpStatusCode.BAD_REQUEST.getCode(), "用户信息不能为空");
        }
        int change = userService.change(user);
        if (change > 0) {
            return Respond.success("修改成功");
        } else {
            return Respond.fail(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), "修改失败");
        }
    }

    @DeleteMapping("/del")
    public Respond delUser(User user) {
        int delete = userService.delete(user);
        if (delete > 0) {
            return Respond.success();
        }
        return Respond.fail(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), "删除失败");
    }

    @RequestMapping("/list")
    public Respond<PageInfo<User>> findAll(User user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Respond.success(new PageInfo<User>(userService.findAll(user)));
    }

    @RequestMapping("/all")
    public Respond<List<User>> userAll(User user) {
        return Respond.success(userService.findAll(user));
    }

    @RequestMapping("/findById")
    public Respond findById(User user) {
        User byId = userService.findById(user);
        if (byId != null) {
            return Respond.success(byId);
        }
        return Respond.fail();
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response, User user) throws IOException {
        List<User> users = userService.findAll(user);
        ExportParams params = new ExportParams("用户列表", "用户列表");
        params.setType(ExcelType.XSSF); // 使用.xlsx格式

        // 导出Excel
        Workbook workbook = ExcelExportUtil.exportExcel(params, User.class, users);

        // 设置响应头和输出流
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        workbook.close();
        outputStream.close();
    }
}
