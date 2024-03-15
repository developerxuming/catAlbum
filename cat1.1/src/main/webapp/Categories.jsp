<!DOCTYPE html>
<!--
// WEBSITE: https://themefisher.com
// TWITTER: https://twitter.com/themefisher
// FACEBOOK: https://www.facebook.com/themefisher
// GITHUB: https://github.com/themefisher/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.entity.vo.CatModel" %>
<%@ page import="java.util.List" %>
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
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body id="body">

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
            <div class="col-md-6" id="categoryList">
                <div class="post">
                    <h3 class="post-title"><%=receivedCatModel.getNames().get(i)%></h3>
                    <div class="post-thumb">
                        <a href="imageWall.jsp?externalAlbumId=<%=receivedCatModel.getAlbumIds().get(i)%>&externalPage=0&externalOrder=newest"
                           title="<%=receivedCatModel.getNames().get(i)%>的照片墙">
                            <img class="img-fluid" src="data:image/jpeg;base64,<%= new String(java.util.Base64.getEncoder().encode(receivedCatModel.getImages().get(i))) %>" alt="">
                        </a>
                    </div>
                    <div class="post-content">
                        <p><strong>品种：</strong><%=receivedCatModel.getVarieties().get(i)%>><br>
                            <strong>毛色：</strong><%=receivedCatModel.getColors().get(i)%><br>
                            <strong>性别：</strong><%=receivedCatModel.getGenders().get(i)%><br>
                            <strong>状况：</strong><%=receivedCatModel.getHealthies().get(i)%><br>
                            <strong>绝育状态：</strong><%=receivedCatModel.getNeutereds().get(i)%><br>
                            <strong>年龄：</strong><%=receivedCatModel.getAges().get(i)%>岁<br>
                            <strong>活动范围：</strong><%=receivedCatModel.getAddresses().get(i)%></p>
                        <a href="imageWall.jsp?externalAlbumId=<%=receivedCatModel.getAlbumIds().get(i)%>&externalPage=0&externalOrder=newest"
                           title="<%=receivedCatModel.getNames().get(i)%>的照片墙" class="btn btn-main"><%=receivedCatModel.getNames().get(i)%>的照片墙</a>
                        <a href="#" title="<%=receivedCatModel.getNames().get(i)%>没有关系网哦！" class="btn btn-main showPopup"><%=receivedCatModel.getNames().get(i)%>关系网</a>
                    </div>
                </div>
            </div>
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
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'Category', // Replace 'example.php' with your actual API endpoint or URL
            dataType: 'json', // Specify the data type expected in the response
            success: function(response) {
                // Callback function to handle the successful response
                // You can perform actions with the response data here
                $('#responseContainer').html(JSON.stringify(response)); // Display response data in the 'responseContainer' div
            },
            error: function(xhr, status, error) {
                // Callback function to handle errors in the AJAX request
                console.log(xhr.responseText); // Log the error message to the console
            }
        });
    })
    let imageContainer = $.getElementById('imageContainer');

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