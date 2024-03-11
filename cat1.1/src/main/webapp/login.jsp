<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2023/11/14
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="jakarta.servlet.jsp.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.entity.User" %>
<%@ page import="com.test.entity.vo.MessageModel" %>
<%@ page import="com.test.controller.UserServlet" %>
<html>
<head>
    <title>请登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png"/>
    <!-- bootstrap.min css -->
    <link rel="stylesheet" href="plugins/bootstrap/bootstrap.min.css">
    <!-- Ionic Icon Css -->
    <link rel="stylesheet" href="plugins/Ionicons/css/ionicons.min.css">
    <!-- animate.css -->
    <link rel="stylesheet" href="plugins/animate-css/animate.css">
    <!-- Magnify Popup -->
    <link rel="stylesheet" href="plugins/magnific-popup/magnific-popup.css">
    <!-- Slick CSS -->
    <link rel="stylesheet" href="plugins/slick/slick.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    MessageModel receivedMessageModel = (MessageModel) request.getAttribute("messageModel");
    if (receivedMessageModel != null) {
        out.println("Received Message Model: " + receivedMessageModel.getMsg());
    }
%>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    Login
                </div>
                <div class="card-body">
                    <form action="login" method="post" id="loginForm">
                        <div class="form-group">
                            <label for="studentid">学号</label>
                            <input type="number" class="form-control" id="studentid" name="studentid"
                                   value="${messageModel.object.studentid}" required>
                        </div>

                        <div class="form-group">
                            <label for="password">密码</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <span id="message" class="text-muted">${messageModel.msg}</span> <br>
                        <button type="button" class="btn btn-primary" onclick="btnLogin()">Login</button>
                        <button type="button" class="btn btn-link" onclick="window.location.href='signin.jsp'">
                            Register
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Main jQuery -->
<script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.1 -->
<script type="text/javascript" src="plugins/bootstrap/bootstrap.min.js"></script>
<!-- slick Carousel -->
<script type="text/javascript" src="plugins/slick/slick.min.js"></script>
<script type="text/javascript" src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<!-- filter -->
<script type="text/javascript" src="plugins/shuffle/shuffle.min.js"></script>
<script type="text/javascript" src="plugins/SyoTimer/jquery.syotimer.min.js"></script>
<!-- Google Map -->
<script type="text/javascript"
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
<script type="text/javascript" src="plugins/google-map/map.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script>
    function btnLogin() {
        const stuid = $("#studentid").val();
        const upwd = $("#password").val();
        if (isEmpty(stuid)) {
            $("#message").html("用户名不可为空");
            return;
        }
        if (isEmpty(upwd)) {
            $("#message").html("用户密码不可为空");
            return;
        }
        $("#loginForm").submit()
    }

    function isEmpty(str) {
        if (str == null || str.trim() === "") {
            return true;
        }
        return false;
    }
</script>
</body>
</html>
