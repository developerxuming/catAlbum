<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2023/11/18
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>花椒喵汪救助队</title>
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
                    <a href="CategoriesAjax.jsp" class="btn btn-main animated fadeInUp"> 分类目录</a>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Wrapper Start -->
<section class="about section">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <div class="block">
                    <div class="section-title">
                        <h2> 关于我们</h2>
                        <p> &emsp; &emsp;
                            网站注册于2023年9月11日，域名购于2023年9月29日。创建网站的初衷是希望能够将神经网络模型输出为一个直接可用的模型，同时也记录一些个人学习的过程</p>
                    </div>
                    <p>&emsp; &ensp; 花椒喵汪抢险队是华东交通大学在校学生于2021年1月24日自发建立的线上救助组织。我们致力于学校猫狗救助、送养、知识宣传等各项工作。
                        我们既不是社团，也不是社会组织，我们是学生自发创建的爱心线上救助群体。
                        <br>&emsp; &ensp;该网站基于themfisher开源网站及github开源项目打造，由于目前相关功能仍在开发阶段，只有单独的浏览功能，
                        目前bug仍然较多，无法把项目完全上传。作为一个较大的项目开发与学习周期较长，目前已花费近几个月时间，
                        希望网站整体的稳定版本（除图像识别功能外）能够在春节上线。<br>同时，也希望对前后端开发感兴趣的同学能够加入网站未来的开发与运营，
                        </p>
                </div>
            </div><!-- .col-md-7 close -->
            <div class="col-md-5">
                <div class="block">
                    <img src="images/Index3.jpg" alt="Img">
                </div>
                <p align="right"><br> ——摄于孔目湖,2023年9月 </p>
            </div><!-- .col-md-5 close -->
        </div>
    </div>
</section>

<!-- Call to action Start -->
<section class="call-to-action bg-1 section-sm overly">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="block">
                    <h2 class="mb-3">关于花椒的流浪猫</h2>
                    <p></p>
                    <a class="btn btn-main btn-solid-border" href="coming_soon.jsp">关于开发者</a>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- footer -->
<iframe src="footer.jsp" frameborder="0" width="100%" height="255"></iframe>

<!--Scroll to top-->
<div id="scroll-to-top" class="scroll-to-top">
    <span class="icon ion-ios-arrow-up"></span>
</div>
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
    function loadName() {
        // 直接跳转到目标页面
        window.location.href = "catName";
    }
    // 通过ID选择器获取按钮并为其添加点击事件
    $(document).ready(function () {
        $("#loadname").on("click", function () {
            loadName();
        });
    });
</script>
</body>
</html>
