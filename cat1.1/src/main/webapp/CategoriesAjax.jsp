<!DOCTYPE html>
<!--
// WEBSITE: https://themefisher.com
// TWITTER: https://twitter.com/themefisher
// FACEBOOK: https://www.facebook.com/themefisher
// GITHUB: https://github.com/themefisher/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<!-- 目录部分 -->
<div class="page-wrapper">
    <div class="container">
        <div class="row" id="showCategory">
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
<script src="js/script.js"></script>
<script>
    // 从 URL 中解析参数
    function getUrlParameter(name) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]')
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)')
        var results = regex.exec(location.search)
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '))
    }

    // 从 URL 中提取参数
    let region = getUrlParameter('region');
    $.get("getCategory" + (region ? '?region=' + region : ''), function(data) {
        // 当请求成功完成时，处理返回的 JSON 数据
        // displayCategories(data);
        console.log(data)
        showCategory(data)
    }).fail(function(xhr, status, error) {
        // 处理请求失败的情况
        console.error('Failed to fetch categories. Error code: ' + xhr.status);
    });

    function showCategory(data) {
        var categoryContainer = document.getElementById('showCategory');
        // 创建文档片段
        var fragment = document.createDocumentFragment();
        // 清空原有的图片显示区域
        categoryContainer.innerHTML = '';
        // 循环构建文档片段
        for (let i = 0; i < data.length; i++) {
            let block =
                '<div class="col-md-6">' +
                '<div class="post">' +
                '<h3 class="post-title">' + data[i]['name'] + '</h3>' +
                '<div class="post-thumb">' +
                '<a href="imageWall.jsp?externalAlbumId=' + data[i]['albumId'] + '&externalPage=0&externalOrder=newest" title="' + data[i]['name'] + '的照片墙">' +
                '<div style="height: 300px;">' +
                '<img class="img-fluid" src="data:image/jpeg;base64, ' + data[i]['image'] + '" alt="1" style="max-height: 300px; max-width: 100%;">' +
                '</div>' +
                '</a>' +
                '</div>' +
                '<div class="post-content">' +
                '<p><strong>品种：</strong>' + data[i]['variety'] + '<br>' +
                '<strong>毛色：</strong>' + data[i]['appearance'] + '<br>' +
                '<strong>性别：</strong>' + data[i]['gender'] + '<br>' +
                '<strong>状况：</strong>' + data[i]['healthy'] + '<br>' +
                '<strong>绝育状态：</strong>' + data[i]['neutered'] + '<br>' +
                '<strong>年龄：</strong>' + data[i]['age'] + '<br>' +
                '<strong>地区：</strong>' + data[i]['region'] + '<br>' +
                '<strong>具体活动范围：</strong>' + data[i]['address'] + '<br>' +
                '<strong>性格特征：</strong>' + data[i]['other'] + '</p>' +
                '<a href="imageWall.jsp?externalAlbumId=' + data[i]['albumId'] + '&externalPage=0&externalOrder=newest" title="' + data[i]['name'] + '的照片墙" class="btn btn-main">' + data[i]['name'] + '的照片墙</a>' +
                '<a href="#" title="' + data[i]['name'] + '没有关系网哦！" class="btn btn-main showPopup">' + data[i]['name'] + '的关系网</a>' +
                '</div>' +
                '</div>' +
                '</div>';

            // 将HTML字符串转换为DOM节点，并添加到文档片段中
            var temp = document.createElement('div');
            temp.innerHTML = block;
            fragment.appendChild(temp.firstChild);
        }
        // 将文档片段一次性添加到DOM中
        categoryContainer.appendChild(fragment);
    }


    $.addEventListener("DOMContentLoaded", function () {
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