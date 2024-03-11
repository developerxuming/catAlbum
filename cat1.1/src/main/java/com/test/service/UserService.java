package com.test.service;

import com.test.entity.User;
import com.test.entity.vo.MessageModel;
import com.test.mapper.UserMapper;
import com.test.util.GetSqlSession;
import com.test.util.StringUtil;
import org.apache.ibatis.session.SqlSession;
/**
 * 普通代码存在的问题
 * 1，SQL注入：
 * 代码在userMapper.queryUser(studentid)调用中使用字符串拼接来构建SQL查询，这使其容易受到SQL注入攻击的影响。建议使用参数化查询或预处理语句来防范此类攻击。
 * 2，明文密码传输：
 * 用户密码以明文形式传递到数据库查询中，这可能在数据传输过程中被窃听。为了增加安全性，应该使用加密技术，例如使用哈希函数对密码进行哈希处理。
 * 3，硬编码的数据库连接信息：
 * 数据库连接信息（如通过GetSqlSession.createSqlSession()获取的数据库会话）可能包含敏感信息，并且在代码中是硬编码的。最好将这些信息存储在安全的配置文件中，而不是硬编码在代码中。
 *,4，异常处理不足：
 * 代码没有足够的异常处理机制，例如在数据库连接失败或查询异常时可能导致未处理的异常。适当的异常处理对于确保系统的稳定性和安全性至关重要。
 *,5，密码比较方式不安全：
 * 用户密码的比较使用了!=，这不是最安全的比较方式。应该使用安全的密码哈希算法，而不是明文密码比较，以提高密码安全性。
 */
public class UserService {
    /**
     * 用户登录
     * 1，参数非空判断
     * 参数为空则将状态吗，提示信息，回显数据设置到消息模型对象中，返回消息模型对象
     * 2，盗用dao层的查询方法，通过用户名查询用户对象
     * 3，判断用户对象是否为空
     * 如果为空，则将状态码，提示信息，回显数据设置到消息模型对象中，返回罅隙模型对象
     * 4，判断数据库中查询到的用户密码与前台传递过来的密码作比较
     * 如果不相等，将状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
     * 5，登录成功，成功状态、提示信息、用户对象设置消息模型对象，并return
     * @param studentid
     * @param password
     */
    public MessageModel userLogin(String studentid, String password) {
        MessageModel messageModel = new MessageModel();

        // 回显数据
        User u = new User();
        u.setStudentid(studentid);
        u.setUserpassword(password);
        messageModel.setObject(u);
        // 1，参数非空判断
        if (StringUtil.isEmpty(studentid) || StringUtil.isEmpty(password)) {
            //将状态吗，提示信息，回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("用户姓名和密码不能为空!");
            return messageModel;
        }
        // 2，盗用dao层的查询方法，通过用户名查询用户对象
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.queryUser(studentid);
        // 3，判断用户对象是否为空
        if (user == null) {
            //将状态吗，提示信息，回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("用户不存在!");
            return messageModel;
        }
        // 4，判断数据库中查询到的用户密码与前台传递过来的密码作比较
        if (!password.equals(user.getUserpassword())) {
            //如果不相等，将状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("用户密码不正确!");
            return messageModel;
        }
        // 5，登录成功，成功状态、提示信息、用户对象设置消息模型对象，并return
        messageModel.setCode(1);
        messageModel.setObject(user);

        return messageModel;
    }
}
