package com.test.service;

import com.test.entity.User;
import com.test.entity.vo.MessageModel;
import com.test.mapper.UserMapper;
import com.test.util.GetSqlSession;
import com.test.util.StringUtil;
import com.test.util.StudentID;
import org.apache.ibatis.session.SqlSession;
import com.test.util.StudentID;

public class SignService {
    private StudentID studentID;
    public static MessageModel userSignin(String studentid, String name, String password, Integer gender, String college, String registertime) {
        MessageModel messageModel = new MessageModel();
        // 回显数据
        User u = new User();
        u.setStudentid(studentid);
        u.setUsername(name);
        u.setUserpassword(password);
        messageModel.setObject(u);

        // 1，参数非空判断
        if (StringUtil.isEmpty(studentid) || StringUtil.isEmpty(password) || StringUtil.isEmpty(name)) {
            //将状态吗，提示信息，回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("学号，用户名和密码不能为空!");
            return messageModel;
        }
        // 2，盗用dao层的查询方法，通过用户名查询用户对象
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User uid = userMapper.queryUser(studentid);
        User uname = userMapper.queryUser(name);
        // 3，判断用户名是否存在
        if (uname != null) {
            //将状态吗，提示信息，回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("用户名已存在!");
            return messageModel;
        }
        // 4，判断学号是否存在
        if (uid != null) {
            //将状态吗，提示信息，回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("学号已存在!");
            return messageModel;
        }
        if (!StudentID.grade(studentid)) {
            // 检查学号信息是否符合要求
            messageModel.setCode(0);
            messageModel.setMsg("学号不合法！");
            return messageModel;
        }
        // 5，用户不存在将新用户信息添加到数据库中
        User newUser = new User();
        newUser.setStudentid(studentid);
        newUser.setUsername(name);
        newUser.setUserpassword(password);
        newUser.setUsergender(gender);
        newUser.setUsercollege(college);
        newUser.setRegistertime(registertime);

        int result = userMapper.insertUser(newUser); // 假设在UserMapper接口中有一个名为insertUser的方法

        if (result > 0) {
            // 用户添加成功
            session.commit(); // 提交事务
            messageModel.setCode(1);
            messageModel.setMsg("用户注册成功!");
            session.close(); // 关闭SqlSession
            messageModel.setObject(newUser);
            return messageModel;
        } else {
            // 用户添加失败
            session.rollback(); // 回滚事务
            messageModel.setCode(0);
            messageModel.setMsg("用户注册失败，请稍后重试!");
            session.close(); // 关闭SqlSession
            return messageModel;
        }
    }
}
