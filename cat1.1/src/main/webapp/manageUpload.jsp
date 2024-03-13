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
    <div class="row">
        <div class="form-check mb-3">
            <label class="form-check-label">
                <input type="file" accept="image/*" id="imageInput" multiple="multiple">
            </label>
        </div>
        <hr>
        <button id="uploadButton">上传图片</button>
            <!-- <input type="file" id="imageInput" class="form-control-file"> -->
        <div class="row" id="imageShow"></div>
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
<!-- <script src="js/profileAPI.js"></script> -->
<script>
    $('#imageInput').on('change',function() {
        let fileInput = document.getElementById('imageInput');
        let files = fileInput.files;
        if (!files.length) {
            alert("请选择图片");
            return
        }
        imageTempShow(files)
    })

    function imageTempShow(files) {
        console.log("上传的文件为:", files);
        let imageTempContainer = document.getElementById('imageShow');
        // 在添加新图片之前只清空一次容器
        imageTempContainer.innerHTML = '';

        let Element;
        for (let i = 0; i < files.length; i++) {
            let file = files[i];
            console.log("第 " + (i+1) + " 张图片：" + file.name);

            let reader = new FileReader();
            reader.onload = function(event, num) {
                let imageUrl = event.target.result;
                Element = '<div class="col-md-3 col-lg-3">' +
                    '<div class=\"portfolio-item shuffle-item grid-item\">' +
                    '<img src="' + imageUrl + '" alt="" height="150">' +
                    '<div class="portfolio-hover">' +
                    '<div class="portfolio-content">' +
                    '<a class="delete-button portfolio-popup" data-index="' + num + '"><i class="icon ion-android-delete"></i></a>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                imageTempContainer.innerHTML += Element;
            };
            reader.readAsDataURL(file,i);
        }
        // 移除之前绑定的事件监听器，再重新添加
        imageTempContainer.removeEventListener('click', handleDeleteButtonClick);
        imageTempContainer.addEventListener('click', function(event) {
            console.log("新的事件监听器被添加")
            handleDeleteButtonClick(event, files);
        });
    }

    // 定义删除按钮点击事件处理函数
    function handleDeleteButtonClick(event, files) {
        console.log("被触发")
        if (event.target.classList.contains('delete-button')) {
            let index = parseInt(event.target.getAttribute('data-index'));
            console.log("index为：",index)
            // 移除文件列表中对应位置的文件
            files.remove(index);
            console.log("newFile为：", files)
            // 重新展示图片
            imageTempShow(files);
        } else {
            console.log()
        }
    }
  </script>
</body>
</html>
