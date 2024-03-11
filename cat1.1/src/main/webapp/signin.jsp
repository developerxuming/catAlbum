<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.entity.User" %>
<html>
<head>
    <title>请注册</title>
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
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    Register
                </div>
                <div class="card-body">
                    <form action="Signin" method="post" id="registrationForm">
                        <!-- Add registration form fields here -->
                        <div class="form-group">
                            <label for="newStudentid">学号</label>
                            <input type="text" class="form-control" id="newStudentid" name="studentid"
                                   placeholder="请输入您的16位学号" value="${messageModel.object.studentid}"
                                   maxlength="16" required>
                        </div>

                        <div class="form-group">
                            <label for="newName">用户名</label>
                            <input type="text" class="form-control" id="newName" name="name"
                                   placeholder="请输入您的名字，半角字符长度最多为12" maxlength="12" required>
                        </div>

                        <!-- 密码输入框 -->
                        <div class="form-group">
                            <label for="newPassword">请输入密码</label>
                            <input type="password" class="form-control" id="newPassword" name="password"
                                   placeholder="请输入密码，最长15个字符" maxlength="15" required>
                        </div>

                        <!-- 再次输入密码，并添加一个id属性为confirmPassword -->
                        <div class="form-group">
                            <label for="confirmPassword">再次输入密码</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                                   placeholder="请再次输入密码" maxlength="15" required>
                            <!-- 提示信息 -->
                            <span id="passwordMismatch" class="text-danger"></span>
                        </div>

                        <div class="form-group">
                            <label>性别</label><br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="gender保密" value="保密"
                                       required>
                                <label class="form-check-label" for="gender保密">保密</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="gender男" value="男"
                                       required>
                                <label class="form-check-label" for="gender男">男</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="gender女" value="女"
                                       required>
                                <label class="form-check-label" for="gender女">女</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="newCollege">学院</label>
                            <select class="form-control" id="newCollege" name="college" required>
                                <option value="保密" selected>保密</option>
                                <option value="信息">信息</option>
                                <option value="软件">软件</option>
                                <option value="理学">理学</option>
                                <option value="天佑">天佑</option>
                                <option value="土木">土木</option>
                                <option value="交运">交运</option>
                            </select>
                        </div>
                        <span id="registrationMessage" class="text-muted">${messageModel.msg}</span> <br>
                        <button type="button" class="btn btn-primary" onclick="btnRegister()">Register</button>
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
<script type="text/javascript" src="js/script.js"></script>
<script>
    $(document).ready(function () {
        // 在页面加载完成后，为密码输入框绑定事件
        $("#newPassword, #confirmPassword").on("keyup", function () {
            // 获取两次输入的密码
            var password1 = $("#newPassword").val();
            var password2 = $("#confirmPassword").val();
            // 检查密码是否一致
            if (password1 === password2) {
                // 如果一致，清空提示信息
                $("#passwordMismatch").text("");
            } else {
                // 如果不一致，显示提示信息
                $("#passwordMismatch").text("两次输入的密码不一致");
            }
        });
        // 设置默认选择
        $('#gender保密').prop('checked', true);
    });

    function btnRegister() {
        // 获取两次输入的密码
        var password1 = $("#newPassword").val();
        var password2 = $("#confirmPassword").val();
        // 检查密码是否一致
        if (password1 !== password2) {
            // 如果不一致，显示提示信息
            alert("密码不一致，请重试");
            return;
        }
        const stuid = $("#newStudentid").val();
        const name = $("#newName").val()
        const upwd = $("#newPassword").val();
        const gender = $("input[name='gender']:checked").val();
        const college = $("#newCollege option:selected").data("value");
        var isNumeric = /^\d+$/.test(stuid);  // 是否为数字布尔值
        // 学号检查
        if (isEmpty(stuid)) {
            $("#registrationMessage").html("学号不可为空");
            return;
        }
        if (!isNumeric) {
            // 如果不是纯数字，显示弹窗
            alert("学号必须为纯数字，请重新输入。");
            $("#newStudentid").val("");
            return;
        }
        // 用户名检查
        if (isEmpty(name)) {
            $("#registrationMessage").html("用户名不可为空");
            return;
        }
        // 密码检查
        if (isEmpty(upwd)) {
            $("#registrationMessage").html("密码不可为空");
            return;
        }
        // 将性别值添加到表单中
        $("<input>").attr({
            type: "hidden",
            id: "gender",
            name: "gender",
            value: gender
        }).appendTo("#registrationForm");
        // 添加学院
        $("<input>").attr({
            type: "hidden",
            id: "college",
            name: "college",
            value: college
        }).appendTo("#registrationForm");
        // 表单上传
        $("#registrationForm").submit()
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

