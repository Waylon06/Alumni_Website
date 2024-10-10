package com.school.service;

import com.school.models.User;

import java.util.List;

public interface IUserService {
    /**
     * 用户登录
     *
     * @param user 登录实体
     * @return 登录成功的用户实体，反之为null
     */
    User verifyUser(User user);


    /**
     * 用户注册（可以只返回注册成功的id）
     *
     * @param user 注册用户实体
     * @return 返回注册成功实体，反之为Null
     */
    int register(User user);

    int change(User user);

    int delete(User user);

    List<User> findAll(User user);

    User findById(User user);
}
