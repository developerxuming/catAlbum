<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2023/12/21
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.util.StringModel" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>图片上传</title>
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
    <style>
        .preview-image {
            max-width: 100%;
            height: auto;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<% StringModel nameModel = (StringModel) request.getAttribute("nameModel"); %>
<div class="container mt-5">
    <h2>相册图片上传</h2>
    <form id="imageForm">
        <div class="form-group">
            <label for="name">学院</label>
            <select class="form-control" id="name" name="name" required>
            <% for (String i : nameModel.getStr()) { %>
                <option value="<%= i %>" selected><%= i %></option>
            <% } %>
            </select>
        </div>
        <div class="form-group">
            <label for="imageInput">选择图片</label>
            <input type="file" class="form-control-file" id="imageInput" accept="image/*" multiple>
        </div>
        <div class="form-group">
            <div class="row" id="previewContainer"></div>
        </div>
        <button type="button" class="btn btn-primary" id="uploadButton">上传到后端</button>
    </form>
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
<script src="js/upload.js"></script>
<script>
    $('#uploadButton').on('click', function () {
        var files = $('#imageInput')[0].files;
        var selectedName = $('#name').val();

        if (files.length > 0) {
            var formData = new FormData();
            formData.append('name', selectedName);
            for (var i = 0; i < files.length; i++) {
                formData.append('images', files[i]);
            }

            $.ajax({
                url: 'upload', // Update the URL to match your servlet mapping
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                    // Handle the success response from the server
                    console.log('Upload successful:', response);
                    alert(response);
                    window.location.reload();
                },
                error: function (error) {
                    // Handle the error response from the server
                    console.error('Upload failed:', error);
                }
            });
        } else {
            alert('请先选择图片');
        }
    });
</script>
</body>
</html>
