<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2023/11/29
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.entity.Image" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="com.test.entity.entityAPI.ImageSearchInfo" %>
<%@ include file="header.jsp" %>
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

</head>
<body>
<%--
前端流程表
1，页面被刷新，进入页面
2，检查imageSearchInfo值，是否为空
3，如果为空
4，  以字符串格式初始化：albumIdParam=0，current_pageParam=0，last_pageParam=0，totalParam=0，
     orderParam=newest
5，否则（不为空条件）
6，  读取albumIdParam
--%>
<!-- Portfolio Start -->
<section class="portfolio-work">
    <div class="container">
        <div class="row">
            <!-- Category Filters on the Left -->
            <div class="col-md-3">
                <div class="block">
                    <div class="portfolio-menu">
                        <div class="btn-group btn-group-toggle btn-group-vertical " data-toggle="buttons">
                            <form action="image" method="post" id="imageForm">
                                <h4 class="widget-title">分类</h4>
                                <input type="hidden" name="albumId" value="">
                                <input type="hidden" name="page" value="">
                                <input type="hidden" name="order" value="">
                                <button class="btn btn-sm btn-primary" onclick="btnImage('0_0_default')" style="width: 200px;">所有图片
                                </button>
                                <button class="btn btn-sm btn-primary" onclick="btnImage('41_0_default')" style="width: 200px;">贝壳
                                </button>
                                <button class="btn btn-sm btn-primary" onclick="btnImage('52_0_default')" style="width: 200px;">馋馋
                                </button>
                                <button class="btn btn-sm btn-primary" onclick="btnImage('50_0_default')"
                                        value="50_0_default" style="width: 200px;">杂图
                                </button>
                            </form>
                        </div>
                    </div>
                    <!-- Widget tag -->
                    <div class="portfolio-menu widget widget-category">
                        <h4 class="widget-title">其他</h4>
                        <ul class="widget-category-list">
                            <li><a id="loadname">上传图片</a>
                            </li>
                            <li><a href="newcat.jsp">增加新居民</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-md-9" >
                <div class="row shuffle-wrapper" id="imageContainer"></div>
<%--                <nav aria-label="Page navigation example">--%>
<%--                    <ul class="pagination post-pagination">--%>
<%--                        <li class="page-item">--%>
<%--                            <button class="page-link" onclick="btnImage('<%= albumIdParam %>_<%= previousPage %>_default')" value="0_1_default">首页--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                        <li class="page-item">--%>
<%--                            <button class="page-link" onclick="btnImage('<%= albumIdParam %>_<%= previousPage %>_default')"--%>
<%--                                    value="0_<%= previousPage %>_default">上一页--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                        <li class="page-item"><span class="page-link"><%= pageParam %> / <%= last_pageParam %></span></li>--%>
<%--                        <li class="page-item">--%>
<%--                            <button class="page-link" onclick="btnImage('<%= albumIdParam %>_<%= nextPage %>_default')"--%>
<%--                                    value="0_<%= nextPage %>_default"> 下一页--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                        <li class="page-item">--%>
<%--                            <button class="page-link" onclick="btnImage('0_<%= last_pageParam %>_default')"--%>
<%--                                    value="0_<%= last_pageParam %>_default">尾页--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </nav>--%>
            </div>
        </div>
    </div>
</section>

<iframe src="footer.jsp" frameborder="0" width="100%" height="255"></iframe>
<!--Scroll to top-->
<div id="scroll-to-top" class="scroll-to-top">
    <span class="icon ion-ios-arrow-up"></span>
</div>
<!-- Main jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
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
    // 初始化全局参数
    var albumId = "0";
    var page = "0";
    var order = "newest";
    // 从URL中提取参数
    const urlParams = new URLSearchParams(window.location.search);
    // 用于初始页面，当进入该页面时，检查url里是否存在参数，如果存在参数则按已有的参数读取图片
    if (urlParams.has("externalAlbumId")) {  // 如果urlParams有参数
        externalAlbumId = urlParams.get('externalAlbumId');
        externalPage = urlParams.get('externalPage');
        externalOrder = urlParams.get('externalOrder');
        console.log(externalAlbumId);
        console.log(externalPage);
        console.log(externalOrder);
        var buttonValue = externalAlbumId + '_' + externalPage + '_' +externalOrder;
        console.log(buttonValue)
        btnImage(buttonValue);
    } else {
        console.log("未检测到输入参数")
    }

    // 该方法用于将表单发送至后端和更新参数
    function btnImage(value) {
        console.log("Button Value:", value);  // 打印值
        var values = value.split('_');  // 以_作为分隔符获得值
        // 读取value的值，用于更新全局参数
        albumId = values[0]
        page = values[1]
        order = values[2]
        // 使用Ajax提交表单数据
        $.ajax({
            type: "POST", // 或者 "GET"，取决于您的需求
            url: "image", // 这里填写您的服务器端处理请求的URL
            data: {
                albumId: albumId,
                page: page,
                order: order
            },
            dataType: 'json',
            success: function(response) {
                // console.log("Response from server:", response);
                // 处理返回的 JSON 数据
                // 示例：假设返回的数据中有一个名为 "data" 的字段
                var jsonData = JSON.stringify(response); // 将返回的 JSON 字符串解析为对象
                var responseData = JSON.parse(jsonData); // 将返回的 JSON 字符串解析为对象
                // 进行进一步的处理，根据需要更新页面内容等
                // console.log(responseData.data.data)
                readBase(responseData.data)
                readImage(responseData.data.data)
            },
            error: function(xhr, status, error) {
                console.error("Error occurred:", error); // 输出错误信息
                // 在这里可以处理发生的错误情况
            }
        });
    }

    function readBase(data) {  // 读相册基础数据
        var from = data.from; // 读取该页面的第一章图片的序号
        var to = data.to; // 读取该页面最后一张的图片序号
        var total = data.total; // 读取该相册图片总数
        var perpage = data.per_page // 读取每页图片默认数量
        var current_page = data.current_page;  // 读取当前页码
        var first_page_url = data.first_page_url; // 首页url
        var last_page = data.last_page;  // 尾页页码
        var last_page_url = data.last_page_url; // 尾页url
        var next_page_url = data.next_page_url; // 下一页url
        var prev_page_url= data.prev_page_url; // 当前页url
    }

    function readImage(data) {
        // 获取图片容器
        var imageContainer = document.getElementById('imageContainer');
        // 清空原有的图片显示区域
        imageContainer.innerHTML = '';
        // 遍历图片数据，并创建图片元素
        for (var i = 0; i < data.length; i++) {
            var image = data[i];
            // console.log(image.links.thumbnail_url)
            var imgElement =
                // '<div class="shuffle-wrapper">' +
                '<div class="col-lg-4 col-sm-6 portfolio-item shuffle-item">' +
                '<img src="'+ image.links.thumbnail_url +'" alt="" style="max-width: 98%; height: auto;">' +
                '<div class="portfolio-hover">' +
                '<div class="portfolio-content">' +
                '<a href="' + image.links.url + '" class="portfolio-popup"><i class="icon ion-search"></i></a>' +
                '</div>' +
                '</div>' +
                // '</div>' +
                '</div>';
            // 将图片元素添加到图片容器中
            imageContainer.innerHTML += imgElement;
        }
    }

    function loadName() {
        // 直接跳转到目标页面
        window.location.href = "catName";
    }

    // 通过ID选择器获取按钮并为其添加点击事件
    // $(document).ready(function () {
    //     $("#loadname").on("click", function () {
    //         loadName();
    //     });
    // });
</script>
</body>
</html>
