<!-- <%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2023/11/29
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%> -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.json.JSONObject" %>
<%@ include file="header.jsp" %>
<!-- 载入页面流程
1，载入页面，检查进入页面时是否含参，
2，如果含参，用URLSearchParams读取参数，直接在js中发起请求
3，否则，使用默认参数在js中发起请求
4，获取response，更新参数和按键状态，显示页面信息
内容更新
1，根据按钮选择激活的方式，如果按钮名为具体的相册或是首页，则以fetchImageData（输入信息）发起请求
2，如果按钮为上一页，下一页，尾页，这通过fetchImageData_url（输入url）发起请求
3，获取response，更新参数和按键状态，显示页面信息 -->
<html>
<head>
    <title>照片墙</title>
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
    <link rel="stylesheet" href="css/sidebar.css">
    <style>
        .grid-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); /* 自动填充列 */
            grid-auto-rows: auto; /* 自动调整行高 */
            gap: 10px; /* 图片间距 */
        }
    
        .grid-item {
            width: 100%;
            height: 100%;
            position: relative; /* 相对定位 */
            overflow: hidden; /* 隐藏溢出内容 */
            border-radius: 10px; /* 图片圆角 */
        }
    
        .grid-item img {
            width: 100%;
            height: 100%;
            object-fit: cover; /* 图片填充容器 */
        }

        .grid-item::after {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            border: 3px solid rgb(175, 173, 255);
            opacity: 0;
            transition: opacity 0.3s ease;
            box-sizing: border-box;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); /* 添加阴影效果 */
            background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.3)); /* 添加渐变背景 */
        }

        .grid-item:hover::after {
            opacity: 1; /* 鼠标悬浮时显示 */
        }
    </style>
</head>
<body>

<!-- Portfolio Start -->
<section class="portfolio-work">
    <div class="container">
        <div class="row">
            <!-- Category Filters on the Left -->
            <div class="col-md-2">
                <div class="block">
                    <div class="portfolio-menu widget widget-category">
                        <h4 class="widget-title">其他</h4>
                        <ul class="widget-category-list">
                            <li><a id="loadname">上传图片</a>
                            </li>
                            <li><a href="newcat.jsp">增加新居民</a>
                            </li>
                        </ul>
                    </div>
                    <br>
                    <div class="portfolio-menu widget widget-category">
                        <h4 class="widget-title">相册分类目录</h4>
                        <ul class="nav-links widget-category-list">
                            <li>
                                <div class="">
                                    <a href="#" class="btn btn-sm btn-primary arrow">南区</a>
                                </div>
                                <ul class="sub-menu" id="southContainer"></ul>
                            </li>
                            <li>
                                <div class="">
                                    <a href="#" class="btn btn-sm btn-primary arrow">北区</a>
                                </div>
                                <ul class="sub-menu" id="northContainer"></ul>
                            </li>
                            <li>
                                <div class="">
                                    <a href="#" class="btn btn-sm btn-primary arrow">领养</a>
                                </div>
                                <ul class="sub-menu" id="adoptContainer"></ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-md-10" >
                <div id="headName"></div>
                <!-- <div class="shuffle-wrapper"></div> -->
                <div class="grid-container" id="imageContainer">
                </div><hr>
                <div class="row justify-content-center">
                    <div class="col-auto">
                        <button id="FirstPage" class="btn btn-primary">首页</button>
                        <button id="prevPage" class="btn btn-primary">上一页</button>
                        <span id="pageNumberDisplay" class="mx-3"></span>
                        <button id="nextPage" class="btn btn-primary">下一页</button>
                        <button id="EndPage" class="btn btn-primary">尾页</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<iframe src="footer.jsp" frameborder="0" width="100%" height="255"></iframe>
<!--Scroll to top-->
<div id="scroll-to-top" class="scroll-to-top">
    <span class="icon ion-ios-arrow-up"></span>
</div>
<script>
    let arrow = document.querySelectorAll(".arrow");
    for (var i = 0; i < arrow.length; i++) {
        arrow[i].addEventListener("click", (e)=>{
            let arrowParent = e.target.parentElement.parentElement;
            arrowParent.classList.toggle("showMenu");
        });
    }
</script>
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
<script src="js/imageShowsAPI.js"></script>
</body>
</html>
