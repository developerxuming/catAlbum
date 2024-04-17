<!DOCTYPE html>
<!--
// WEBSITE: https://themefisher.com
// TWITTER: https://twitter.com/themefisher
// FACEBOOK: https://www.facebook.com/themefisher
// GITHUB: https://github.com/themefisher/
已废弃！！请勿使用！！
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.entity.Cat" %>
<%@ page import="com.test.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ include file="header.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>花椒流浪猫目录</title>
    <!-- Mobile Specific Metas
    ================================================== -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Creative Agency Bootstrap Template">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
    <meta name="author" content="Themefisher">
    <meta name="generator" content="Themefisher Airspace Template v1.0">
    <!-- Favicon -->
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
    <link rel="stylesheet" href="plugins/bootstrap-icons-1.11.3/font/bootstrap-icons.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body id="body">
<% List<Category> receivedCategory;
    receivedCategory = (List<Category>) request.getAttribute("Category");
    int len = receivedCategory.size();
%>

<section class="page-title bg-2">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="block">
                    <h1> 猫猫目录</h1>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 目录部分 -->
<div class="page-wrapper">
    <div class="container">
        <div class="row">
            <% for (int i = 0; i < len; i++) { %>
            <div class="col-md-4">
                <div class="post">
                    <h3 class="post-title"><%=receivedCategory.get(i).getName()%></h3>
                    <div class="post-thumb">
                        <a href="imageWall.jsp?externalAlbumId=<%=receivedCategory.get(i).getAlbumId()%>&externalPage=0&externalOrder=newest"
                           title="<%=receivedCategory.get(i).getName()%>的照片墙">
                            <img class="img-fluid" src="data:image/jpeg;base64,<%= new String(java.util.Base64.getEncoder().encode(receivedCategory.get(i).getImage())) %>" alt="" height="100">
                        </a>
                    </div>
                    <div class="post-content">
                        <p><strong>品种：</strong><%=receivedCategory.get(i).getVariety()%><br>
                            <strong>毛色：</strong><%=receivedCategory.get(i).getAppearance()%><br>
                            <strong>性别：</strong><%=receivedCategory.get(i).getGender()%><br>
                            <strong>状况：</strong><%=receivedCategory.get(i).getHealthy()%><br>
                            <strong>绝育状态：</strong><%=receivedCategory.get(i).getNeutered()%><br>
                            <strong>年龄：</strong><%=receivedCategory.get(i).getAge()%><br>
                            <strong>地区：</strong><%=receivedCategory.get(i).getRegion()%><br>
                            <strong>具体活动范围：</strong><%=receivedCategory.get(i).getAddress()%><br>
                            <strong>性格特征：</strong><%=receivedCategory.get(i).getOther()%>
                        </p>
                        <a href="imageWall.jsp?externalAlbumId=<%=receivedCategory.get(i).getAlbumId()%>&externalPage=0&externalOrder=newest"
                           title="<%=receivedCategory.get(i).getName()%>的照片墙" class="btn btn-main"><%=receivedCategory.get(i).getName()%>的照片墙</a>
                        <a href="#" title="<%=receivedCategory.get(i).getName()%>没有关系网哦！" class="btn btn-main showPopup"><%=receivedCategory.get(i).getName()%>关系网</a>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>

<iframe src="footer.jsp" frameborder="0" width="100%" height="255"></iframe>
<!--Scroll to top-->
<div id="scroll-to-top" class="scroll-to-top">
    <span class="icon ion-ios-arrow-up"></span>
</div>

<!-- 弹出框 -->
<div class="popup" id="popup">
    <div class="popup-content">
        <span class="close" id="closePopup">&times;</span>
        <p>目前没有关系网哦！</p>
    </div>
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
<!-- Google Map -->
<script src="plugins/google-map/map.js"></script>
<script src="js/script.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {

        // 获取具有类名"showPopup"的所有按钮
        var popupButtons = document.querySelectorAll(".showPopup");

        // 添加事件监听器以显示弹窗
        popupButtons.forEach(function (button) {
            button.addEventListener("click", function (event) {
                event.preventDefault(); // 阻止默认行为
                document.getElementById("popup").style.display = "block";
            });
        });

        // 添加关闭弹窗的事件监听器（与之前的代码相同）
        document.getElementById("closePopup").addEventListener("click", function () {
            document.getElementById("popup").style.display = "none";
        });
    });
</script>
</body>
</html>