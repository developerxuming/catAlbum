<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2023/11/29
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.test.entity.vo.CatModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>跳转测试</title>
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
<body id="body">
<!-- 分类器部分 -->
<section class="slider">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="block">
                    <h1 class="animated fadeInUp"><br></h1>
                    <p class="animated fadeInUp"> 这是一个简单的图像分类器 </br>
                        可以输入图片让神经网络分类，或直接访问分类目录 </p>
                    <a href="classification.jsp" class="btn btn-main animated fadeInUp">图片识别</a>
                    <a id="loadindex" class="btn btn-main animated fadeInUp"> 分类目录</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Main jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.1 -->
<script src="plugins/bootstrap/bootstrap.min.js"></script>
<!-- slick Carousel -->
<script src="plugins/slick/slick.min.js"></script>
<script src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<!-- filter -->
<script src="plugins/shuffle/shuffle.min.js"></script>
<script src="plugins/SyoTimer/jquery.syotimer.min.js"></script>
<script src="js/script.js"></script>
<script>
        // 使用 jQuery 发起 AJAX 请求
        function loadIndex() {
        // 直接跳转到目标页面
        window.location.href = "index";
    }
        // 通过ID选择器获取按钮并为其添加点击事件
        $(document).ready(function () {
        $("#loadindex").on("click", function () {
            loadIndex();
        });
    });
</script>
</body>


