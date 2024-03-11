<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2024/3/6
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="manageHeader.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
    <title>相册管理</title>
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png"/>
    <!-- bootstrap.min css -->
    <link rel="stylesheet" href="plugins/bootstrap/bootstrap.min.css">
    <!-- Ionic Icon Css -->
    <link rel="stylesheet" href="plugins/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="plugins/bootstrap-icons-1.11.3/font/bootstrap-icons.min.css">
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
<div class="container">
    <div class="text-center mb-3">
        <h2 class="mb-0">图片管理器</h2>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-2 col-lg-2">
            <aside class="sidebar pt-5 pt-lg-0 mt-5 mt-lg-0">
                <div class="mb-3">
                    <h3 class="mb-0">图床信息</h3>
                </div>
                <table class="table table-striped">
                    <tbody>
                    <tr>
                        <td>相册数量</td>
                    </tr>
                    <tr>
                        <td id="albumNum">读取中。。</td>
                    </tr>
                    <tr>
                        <td>图片数量</td>
                    </tr>
                    <tr>
                        <td id="imageNum">读取中。。</td>
                    </tr>
                    <tr>
                        <td>目前已占用空间</td>
                    </tr>
                    <tr>
                        <td id="useSpace">读取中。。</td>
                    </tr>
                    <tr>
                        <td>总空间</td>
                    </tr>
                    <tr>
                        <td id="totalSpace">读取中。。</td>
                    </tr>
                    </tbody>
                </table>
            </aside>
        </div>
        <div class="col-md-10 col-lg-10 col-sm-10">
            <div class="row">

                <hr>
                <div class="container" id="message"></div>
            </div>
        </div>
    </div>
</div>

<iframe src="footer.jsp" frameborder="0" width="100%" height="255"></iframe>
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
<script src="js/profileAPI.js"></script>
</body>
</html>
