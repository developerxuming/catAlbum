<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2024/3/12
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="manageHeader.jsp"%>
<html>
<head>
    <title>管理系统（添加新目录）</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
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
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-sm-12">
            <div class="card">
                <div class="card-body">
                    <form method="post" id="newcatForm" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="variety">特征</label>
                            <input type="text" class="form-control" id="variety" name="variety"
                                   placeholder="请描述流浪猫的品种，特征" maxlength="100" required>
                        </div>
                        <div class="form-group">
                            <label for="name">名字</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入您给它的称呼，半角字符长度最多为12" maxlength="12" required>
                        </div>
                        <div class="form-group">
                            <label for="gender">性别</label>
                            <input type="text" class="form-control" id="gender" name="gender"
                               placeholder="请输入它的性别" maxlength="8" required>
                        </div>
                        <div class="form-group">
                            <label for="age">年龄</label>
                            <input type="text" class="form-control" id="age" name="age"
                               placeholder="请输入年龄" maxlength="8" required>
                        </div>
                        <div class="form-group">
                            <label for="address">活动地区</label>
                            <input type="text" class="form-control" id="address" name="address"
                                   placeholder="输入活动范围（如发现的区域，地点，平常的活动轨迹等，50字内）" rows="3" maxlength="100" required>
                        </div>
                        <div class="form-group">
                            <label for="appearance">外貌特征</label>
                            <textarea class="form-control" id="appearance" name="appearance"
                                      placeholder="输入年龄外貌特征（如花色，斑点，毛质等等，50字以内）" rows="5" maxlength="100" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="other">外貌特征</label>
                            <textarea class="form-control" id="other" name="other"
                                      placeholder="输入性格习性（如是否怕人，是否爱咬或者挠，是否适合领养或者流浪，是否绝育，可填未知，50字以内）" rows="5" maxlength="100" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="image1">图片1</label>
                            <input type="file" class="form-control" id="image1" name="image1" accept="image/*" required>
                        </div>
                        <div class="form-group">
                            <label for="image2">图片2</label>
                            <input type="file" class="form-control" id="image2" name="image2" accept="image/*" required>
                        </div>
                        <span id="warmingMessage" class="text-muted">${feedbackModel.msg}</span> <br>
                        <button type="submit" class="btn btn-primary btn-lg" id="submitBtn">确定</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-1 col-sm-12">
            <br>
        </div>
        <div class="col-md-4 col-sm-12">
            <div class="my-4">
                <h4 class="mb-3">流浪猫信息填写说明：</h4>
                <p class="mb-2">请在表中填写您发现的流浪猫的详细信息。这将有助于我们更好地了解情况。</p>
                <p class="mb-2"><strong>填写注意事项：</strong></p>
                <ul class="mb-3">
                    <li>描述猫咪的品种和特征，最多50个字符。</li>
                    <li>填写您发现猫咪的地点或最近观察到的活动地区，最多50个字符。</li>
                    <li>图片上传：请上传两张猫咪的图片，以便更好地识别。</li>
                </ul>
                <p>您可以在目录中随时更改信息</p>
                <p>感谢您的合作！</p>
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
    $(document).ready(function() {
        $('#submitBtn').click(function() {
            // 创建 FormData 对象，用于将表单数据包装成键值对
            let formData = new FormData($('#newcatForm')[0]);
            console.log(formData.get('name'))
            console.log(formData.get('age'))
            console.log(formData.get('other'))
            console.log(formData.get('image1'))
            console.log(formData.get('image2'))
            $.ajax({
                url: 'newCat', // 修改为你的服务器端处理上传的URL
                method: `POST`,
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    // 成功时显示成功信息
                    $('#warmingMessage').html('<div class="alert alert-success">数据提交成功！</div>');
                },
                error: function(xhr, status, error) {
                    // 错误时显示错误信息
                    $('#warmingMessage').html('<div class="alert alert-danger">提交数据时出错：' + error + '</div>');
                }
            });
        });
    });
</script>
</body>
</html>
