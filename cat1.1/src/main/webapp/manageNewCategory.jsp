<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2024/4/13
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="manageHeader.jsp"%>
<html>
<head>
    <title>词条信息更新</title>
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
                    <form method="post" id="categoryForm" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="variety">品种</label>
                            <input type="text" class="form-control" id="variety" name="variety"
                                   placeholder="请描述流浪猫的品种，特征" maxlength="100" required>
                        </div>
                        <div class="form-group">
                            <label for="name">名字</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入您给它的称呼，半角字符长度最多为12" maxlength="12" required>
                        </div>
                        <div class="form-group">
                            <label for="age">年龄</label>
                            <input type="text" class="form-control" id="age" name="age"
                                   placeholder="请输入年龄" maxlength="8" required>
                        </div>
                        <div class="form-group">
                            <label for="gender">性别</label>
                            <select class="form-control" id="gender" name="gender" required>
                                <option value="" disabled selected>请选择性别</option>
                                <option value="公">公</option>
                                <option value="母">母</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="healthy">健康状态</label>
                            <select class="form-control" id="healthy" name="healthy" required>
                                <option value="" disabled selected>请选择健康状态</option>
                                <option value="健康">健康（无明显伤口，疤痕）</option>
                                <option value="较差">受伤（有明显伤口，疤痕）</option>
                                <option value="很差">残疾（有明显骨折，等严重外伤）</option>
                                <option value="怀孕">怀孕</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="neutered">是否绝育</label>
                            <select class="form-control" id="neutered" name="neutered" required>
                                <option value="" disabled selected>请选择绝育状态</option>
                                <option value="已绝育">已绝育（流浪猫绝育会剪耳，流浪猫绝育会剪耳，母猫是剪右耳作为标识，而公猫是剪左耳作为标识。）</option>
                                <option value="未绝育">未绝育</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="region">地区</label>
                            <select class="form-control" id="region" name="region" required>
                                <option value="" disabled selected>请选择生活地区</option>
                                <option value="南区">南区</option>
                                <option value="北区">北区</option>
                                <option value="领养">已领养</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="address">具体活动地区</label>
                            <input type="text" class="form-control" id="address" name="address"
                                   placeholder="输入活动范围（如发现的区域，地点，平常的活动轨迹等，50字内）" rows="3" maxlength="100" required>
                        </div>
                        <div class="form-group">
                            <label for="appearance">外貌特征</label>
                            <textarea class="form-control" id="appearance" name="appearance"
                                      placeholder="输入年龄外貌特征（如花色，斑点，毛质等等，50字以内）" rows="5" maxlength="100" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="other">性格特征</label>
                            <textarea class="form-control" id="other" name="other"
                                      placeholder="输入性格习性（如是否怕人，是否爱咬或者挠，是否适合领养或者流浪，是否绝育，第一次发现时填第一印象，50字以内）" rows="5" maxlength="100" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="image">图片</label>
                            <input type="file" class="form-control" id="image" name="image" accept="image/*">
                        </div>
                        <input type="hidden" name="isAdmin" value="true">
                        <input type="hidden" id="oldName" name="oldName">
                        <span id="warmingMessage" class="text-muted">${categoryFeedbackModel.msg}</span> <br>
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
                    <li>您的可以无视更新时间间隔管理词条。</li>
                    <li>您的可以随时通过日志文件查看词条的历史修改记录，也可以选择回滚历史修改记录。</li>
                    <li>请尽量详细的描述词条内容，可以更好的帮助大家认识它们。</li>
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
        function getUrlParameter(name) {
            name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]')
            var regex = new RegExp('[\\?&]' + name + '=([^&#]*)')
            var results = regex.exec(location.search)
            return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '))
        }

        // 从 URL 中提取参数
        let name = getUrlParameter('name');
        let opt;
        if (name) {
            opt = "renew"
        } else {
            opt = "new"
        }
        if (name) {
            $.get("getCategoryByName?name=" + name, function(data) {
                console.log(data);
                $("#name").val(data['name']);
                $("#oldName").val(data['name']);
                $("#variety").val(data['variety']);
                $("#age").val(data['age']);
                $("#address").val(data['address']);
                $("#appearance").val(data['appearance']);
                $("#other").val(data['other']);
                $("#healthy").val(data['healthy']);
                if (data['neutered'] === "是") {
                    $("#neutered").val("已绝育");
                } else {
                    $("#neutered").val("未绝育");
                }
                $("#region").val(data['region']);
                $("#gender").val(data['gender']);
            }).fail(function(xhr, status, error) {
                // 处理请求失败的情况
                console.error('Failed to fetch categories. Error code: ' + xhr.status);
            });
        }

        $('#categoryForm').submit(function(event) {
            event.preventDefault(); // 取消表单的默认提交行为
            // 创建 FormData 对象，用于将表单数据包装成键值对
            if ($('#name').val() === "") {
                $('#warmingMessage').html('<div class="alert alert-danger">名字不能为空</div>');
                return;
            }
            if ($('#variety').val() === "") {
                $('#warmingMessage').html('<div class="alert alert-danger">品种不能为空</div>');
                return;
            }
            if ($('#age').val() === "") {
                $('#warmingMessage').html('<div class="alert alert-danger">年龄不能为空</div>');
                return;
            }
            if ($('#address').val() === "") {
                $('#warmingMessage').html('<div class="alert alert-danger">地址不能为空</div>');
                return;
            }
            if ($('#appearance').val() === "") {
                $('#warmingMessage').html('<div class="alert alert-danger">外貌特征不能为空</div>');
                return;
            }
            if ($('#other').val() === "") {
                $('#warmingMessage').html('<div class="alert alert-danger">性格特征不能为空</div>');
                return;
            }
            if (name === "") {
                if (!$(this)[0].files[0]) {
                    $('#warmingMessage').html('<div class="alert alert-danger">性格特征不能为空</div>');
                    return;
                }
            }
            let formData = new FormData($('#categoryForm')[0]);
            formData.append('opt', opt);
            console.log(formData.get('name'))
            console.log(formData.get('other'))
            console.log(formData.get('image'))
            $.ajax({
                url: 'manageCat', // 修改为你的服务器端处理上传的URL
                method: `POST`,
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log(response)
                    if (opt === "new") {
                        if (response["code"] === 200) {
                            if (formData.get('healthy') === "很差") {
                                alert("数据已上传！\n\n    感谢您的协助，我们会持续关注它的健康状况！")
                            } else {
                                alert("数据已上传！感谢您的协助！\n\n    注：如信息有误您可以在一段时间后对信息进行再次修改")
                            }
                            window.location.href = 'CategoriesAjax.jsp';
                        } else {
                            $('#warmingMessage').html('<div class="alert  alert-danger">数据提交失败, 因为'+ response['msg'] +'</div>')
                        }
                    } else {
                        if (response["code"] === 200) {
                            if (formData.get('healthy') === "很差") {
                                alert("数据已更改！\n\n    感谢您的协助，我们会持续关注它的健康状况！")
                            } else {
                                alert("数据已更改！感谢您的协助！\n\n    注：如信息有误您可以在一段时间后对信息进行再次修改")
                            }
                            window.location.href = 'CategoriesAjax.jsp';
                        } else {
                            $('#warmingMessage').html('<div class="alert  alert-danger">数据提交失败, 因为'+ response['msg'] +'</div>')
                        }
                    }
                    // 成功时显示成功信息
                },
                error: function(xhr, status, error) {
                    // 错误时显示错误信息
                    $('#warmingMessage').html('<div class="alert alert-danger">' + error + '</div>');
                }
            });
        });
    });

</script>
</body>
</html>