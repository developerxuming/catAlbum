<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2023/12/23
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.entity.vo.FeedbackModel" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>上传新居民</title>
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
    FeedbackModel Message = (FeedbackModel) request.getAttribute("feedbackModel");
    if (Message != null) {
        out.println("Received Message Model: " + Message.getMsg());
    }
%>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-sm-12">
            <div class="card">
                <div class="card-body">
                    <form action="uploadNewCat" method="post" id="newcatForm" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="name">称呼</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入您给他的称呼，半角字符长度最多为12" maxlength="12" required>
                        </div>
                        <div class="form-group">
                            <label for="variety">特征</label>
                            <input type="text" class="form-control" id="variety" name="variety"
                                   placeholder="请描述流浪猫的品种，特征" maxlength="100" required>
                        </div>
                        <div class="form-group">
                            <label for="address">活动地区</label>
                            <input type="text" class="form-control" id="address" name="address"
                                   placeholder="请描述发现猫猫的地点，或是近期观察到的活动地区" maxlength="100" required>
                        </div>
                        <div class="form-group">
                            <label for="message">补充信息</label>
                            <textarea class="form-control" id="message" name="message"
                                      placeholder="如有补充信息请填写，非必填" rows="5" maxlength="200" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="image1">图片1</label>
                            <input type="file" class="form-control" id="image1" name="image1" accept="image/*" required>
                        </div>
                        <div class="form-group">
                            <label for="image2">图片2</label>
                            <input type="file" class="form-control" id="image2" name="image2" accept="image/*" required>
                        </div>
                        <span id="warmingMessage" class="text-muted">${feedbackModel.msg}</span> <br>
                        <button type="button" class="btn btn-primary btn-lg" onclick="btnfeedback()">确定</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-1 col-sm-12">
            <br>
        </div>
        <div class="col-md-4 col-sm-12">
            <div class="my-4">
            <h4 class="mb-3">流浪猫信息填写说明：</h4>
            <p class="mb-2">请在表中填写您发现的流浪猫的详细信息。这将有助于我们更好地了解猫咪的情况，并提供适当的帮助。</p>
            <p class="mb-2"><strong>填写注意事项：</strong></p>
            <ul class="mb-3">
                <li>称呼：请输入您给猫咪起的名字，最多12个字符。</li>
                <li>特征：描述猫咪的品种和特征，最多100个字符。</li>
                <li>活动地区：填写您发现猫咪的地点或最近观察到的活动地区，最多100个字符。</li>
                <li>补充信息：如有额外信息，请在此填写，最多200个字符。</li>
                <li>图片上传：请上传两张猫咪的图片，以便更好地识别。</li>
            </ul>
            <p>我们在核实后会尽快新增并完善图鉴信息。</p>
            <p>感谢您的合作！</p>
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
    function btnfeedback() {
        console.log("Entering btnfeedback()");
        const Name = $("#name").val();
        const Variety = $("#variety").val()
        const Address = $("#address").val();
        const Image1 = $("#image1")[0].files[0];
        const Image2 = $("#image2")[0].files[0];
        console.log("Image1:", Image1);
        console.log("Image2:", Image2);
        if (isEmpty(Name)) {
            $("#warmingMessage").html("称谓不可为空");
            return;
        }
        if (isEmpty(Variety)) {
            $("#warmingMessage").html("品种不可为空");
            return;
        }
        if (isEmpty(Address)) {
            $("#warmingMessage").html("地址不可为空");
            return;
        }
        if (!hasSelectedImage(Image1)) {
            $("#warmingMessage").html("请上传第一张图片");
            return;
        }
        if (!hasSelectedImage(Image2)) {
            $("#warmingMessage").html("请上传第二张图片");
            return;
        }
        // 表单上传
        $("#newcatForm").submit()
    }
    function isEmpty(str) {
        if (str == null || str.trim() === "") {
            return true;
        }
        return false;
    }
    function hasSelectedImage(file) {
        return file != null;
    }
</script>
</body>
</html>
