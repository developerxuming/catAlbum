<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2024/4/13
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
    <title>数据库信息管理</title>
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
        <h2 class="mb-0">数据库管理</h2>
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
        <div class="col-md-12 col-lg-12 col-sm-12">
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
<script>
    // 从 URL 中解析参数
    function getUrlParameter(name) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]')
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)')
        var results = regex.exec(location.search)
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '))
    }

    function showAlbumMessage(albumList) {
        console.log(albumList)
        let innerHTML =
            '<div class="mb-3">' +
            '<h3 class="mb-0">词条信息</h3>' +
            '</div>';
        innerHTML +=
            '<table class="table table-bordered">' +
            '<thead>' + '<tr>' + '<th>名字</th>' + '<th>品种</th>' + '<th>毛色</th>' + '<th>性别</th>' + '<th>健康状况</th>' + '<th>绝育状态</th>' + '<th>年龄</th>' + '<th>地区</th>' + '</tr>' +
            '</thead>' + '<tbody>';
        for (let i = 0; i<albumList.length; i++) {
            innerHTML += '<tr>' + '<td>' + (i+1) + '</td>' +
                '<td>' + albumList[i]['name'] + '</td>' +
                '<td>' + albumList[i]['id'] + '</td>' +
                '<td>' + albumList[i]['image_num'] + '</td>'
            if (albumList[i]['intro'] === '') {
                innerHTML += '<td>无</td>'
            } else {
                innerHTML += '<td>' + albumList[i]['intro'] + '</td>'
            }
            innerHTML += '<td><button class="btn btn-primary btn-danger btn-sm" onclick="deleteConfirm(' + albumList[i]['id'] + ',\'' + albumList[i]['name'] + '\', ' + albumList[i]['image_num'] + ')">删除相册</button>' +
                '&emsp;<button class="btn btn-primary btn-blue btn-sm" onclick="fetchImage(' + albumList[i]['id'] + ', \'' + albumList[i]['name'] + '\')">管理图片</button> ' +
                '</td>' + '</tr>'
        }
        innerHTML += '</tbody>' +
            '</table>';
        $('#message').html(innerHTML);
    }

    $(document).ready(function() {
        let name = getUrlParameter('name');
        let region = getUrlParameter('region');

        $.get("getCategory" + (region ? '?region=' + region : ''), function(data) {
            // 当请求成功完成时，处理返回的 JSON 数据
            console.log(data)
            showCategory(data)
        }).fail(function(xhr, status, error) {
            // 处理请求失败的情况
            console.error('Failed to fetch categories. Error code: ' + xhr.status);
        });
    });

</script>
</body>
</html>
