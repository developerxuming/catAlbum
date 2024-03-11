<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.entity.User" %>
<%@ page import="com.test.entity.vo.MessageModel" %>
<html>
<head>
    <title>session测试</title>
</head>
<body>
<h1>Display Data from Session</h1>
<%
    // 从 session 中检索数据
    User user = (User) session.getAttribute("user");

    if (user != null) {
        MessageModel messageModel = new MessageModel();
        messageModel.setObject(user);

%>
<p>用户名: <%= user.getUsername() %>
</p>
<%
} else {
%>
<!-- 如果用户未登录，弹出确认框 -->
<script>
    var userNotLoggedIn = confirm("No data found in session. Click OK to go to login page.");
    if (userNotLoggedIn) {
        // 用户点击确认，跳转到登录页面
        window.location.href = "login.jsp";
    } else {
        // 用户点击取消或关闭弹窗，可以在这里执行其他操作或留空
    }
</script>
<%
    }
%>
</body>
</html>
