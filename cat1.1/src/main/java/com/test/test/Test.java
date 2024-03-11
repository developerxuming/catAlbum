package com.test.test;

import com.test.entity.User;
import com.test.mapper.UserMapper;
import com.test.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;


public class Test {
    public static void main(String[] args) {
        //获取sqlsession对象
        SqlSession session = GetSqlSession.createSqlSession();
        //得到对应的mapper
        UserMapper userMapper = session.getMapper(UserMapper.class);
        // 调用方法
        User user = userMapper.queryUser("2021068081000014");
        System.out.println(user);
    }
}
