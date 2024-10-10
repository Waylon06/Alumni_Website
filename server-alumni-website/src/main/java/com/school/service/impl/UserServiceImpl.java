package com.school.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.school.mapper.UserMapper;
import com.school.models.User;
import com.school.service.IUserService;
import com.school.utils.IPasswordEncoder;
import com.school.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final IPasswordEncoder argon2PasswordEncoder;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public UserServiceImpl(UserMapper userMapper, IPasswordEncoder argon2PasswordEncoder) {
        this.userMapper = userMapper;
        this.argon2PasswordEncoder = argon2PasswordEncoder;
    }

    @Override
    public User verifyUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user.getUsername());
//        DigestUtils.md5Digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
        User result = userMapper.selectOne(queryWrapper);
        if (result == null) {
            return null;
        }
        boolean matches = argon2PasswordEncoder.matches(user.getPassword(), result.getPassword());
        if (matches) {
            String token = JwtUtil.createToken(user.getUserId());
            redisTemplate.opsForValue().set("token:" + token, JSON.toJSONString(result), 1, TimeUnit.DAYS);
            System.out.println("token:" + redisTemplate.opsForValue().get("token:"));
            return result;
        } else {
            return null;
        }
    }

    @Override
    public int register(User user) {
        user.setPassword(argon2PasswordEncoder.encode(user.getPassword()));
        user.setAvatar("https://waylon-personal-bucket.oss-cn-chengdu.aliyuncs.com/5590a805b4b14249a6bed711b5985f80.jpg");
        int insert = userMapper.insert(user);
        System.out.println(insert);
        return insert;
    }

    @Override
    public int change(User user) {
        System.out.println(user);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", user.getUserId());
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(argon2PasswordEncoder.encode(user.getPassword()));
            updateWrapper.set("password", user.getPassword());
        }
        if (!StringUtils.isEmpty(user.getEmail())) {
            updateWrapper.set("email", user.getEmail());
        }
        if (!StringUtils.isEmpty(user.getAvatar())) {
            updateWrapper.set("avatar", user.getAvatar());
        }
        if(user.getAdmin() != null && user.getAdmin() != 0) {
            updateWrapper.set("admin", user.getAdmin());
        }
        int i = userMapper.update(null, updateWrapper);
        if (i > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(User user) {
//        int i = userMapper.deleteById(user.getUserId());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", user.getUserId());
        int i = userMapper.delete(userQueryWrapper);
        if (i > 0) {
            return i;
        }
        return 0;
    }

    @Override
    public List<User> findAll(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(user.getEmail())) {
            userQueryWrapper.eq("email", user.getEmail());
        }
        if (!StringUtils.isEmpty(user.getUsername())) {
            userQueryWrapper.eq("username", user.getUsername());
        }
        if (user.getAdmin() != null) {
            userQueryWrapper.eq("admin", user.getAdmin());
        }
        List<User> users = userMapper.selectList(userQueryWrapper);
        return users;
    }

    @Override
    public User findById(User user) {
        User user1 = userMapper.selectById(user.getUserId());
        return user1;
    }
}
