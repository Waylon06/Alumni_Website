package com.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.models.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Waylon
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

