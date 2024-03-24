<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
    <title>顶部导航栏</title>
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
<body>
<%!
    // 获取问候语
    String getGreeting() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(sdf.format(new Date()));
        String greeting;

        if (hour >= 6 && hour < 12) {
            greeting = "早上好";
        } else if (hour >= 12 && hour < 18) {
            greeting = "下午好";
        } else {
            greeting = "晚上好";
        }

        return greeting;
    }
%>
<!-- Header Start -->
<header class="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <nav class="navbar navbar-expand-lg p-0">
                    <a class="navbar-brand" href="welcome.jsp">
                        <img src="images/logo.png" alt="Logo">
                    </a>
                    <div class="navbar-collapse" id="navbarsExample09">
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown @@pages">
                                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">流浪猫目录<span
                                        class="ion-ios-arrow-down"></span></a>
                                <ul class="dropdown-menu" aria-labelledby="dropdown05">
                                    <li><a class="dropdown-item @@about" href="CategoriesAjax.jsp">所有校区</a></li>
                                    <li><a class="dropdown-item @@about" href="CategoriesAjax.jsp?region=南区">南区</a></li>
                                    <li><a class="dropdown-item @@faq" href="CategoriesAjax.jsp?region=北区">北区</a></li>
                                    <li><a class="dropdown-item @@faq" href="CategoriesAjax.jsp?region=已领养">已经领养</a></li>
                                </ul>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link"
                                   href="imageWall.jsp?externalAlbumId=0&externalPage=0&externalOrder=newest">照片墙</a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="coming_soon.jsp">信息分享</a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="adopt.jsp">我要领养</a>
                            </li>
                            <li class="nav-item dropdown @@pages">
                                <a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">其他内容 <span
                                        class="ion-ios-arrow-down"></span></a>
                                <ul class="dropdown-menu" aria-labelledby="dropdown05">
                                    <li><a class="dropdown-item @@about" href="about.jsp">关于花椒流浪猫</a></li>
                                    <li><a class="dropdown-item @@about" href="about.jsp">排行榜</a></li>
                                    <li><a class="dropdown-item @@faq" href="faq.jsp">问题解答</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <a href="checkout.html" class="btn btn-border-tp btn-small ml-3">Purchase Now</a>
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- header close -->
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
</body>
</html>
