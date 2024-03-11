package com.test.mapper;

import com.test.entity.User;

// 用户接口类
public interface UserMapper {
    User queryUser(String studentid);

    int insertUser(User user);
}

