// upload.js

function printImageInfo(file) {
    console.log('文件名:', file.name);
    console.log('文件类型:', file.type);
    console.log('文件大小:', file.size, 'bytes');
    console.log('最后修改时间:', file.lastModified);
}
function deletePreviewImage(index) {
    console.log('删除图片索引:', index);
    // 选择col-md-1类中的index个图片，并删除
    $('#previewContainer').find('.col-md-2').eq(index).remove();  // 选择col-md-1类
    // 更新文件选择框中的文件列表
    var files = $('#imageInput')[0].files;
    var newFiles = [];
    for (var i = 0; i < files.length; i++) {
        if (i !== index) {
            newFiles.push(files[i]);
        }
    }
    // 如果还有剩余图片，则更新文件选择框
    if (newFiles.length > 0) {
        // 创建新的FileList对象
        var newFileList = new DataTransfer();
        for (var j = 0; j < newFiles.length; j++) {
            newFileList.items.add(newFiles[j]);
        }
        // 创建新的文件输入元素
        var newInput = $('<input type="file" class="form-control-file" id="imageInput" accept="image/*" multiple>');
        // 设置新文件列表
        newInput[0].files = newFileList.files;
        // 替换旧的文件输入元素
        $('#imageInput').remove();
        $('#imageForm').append(newInput);
        // 打印图片信息到控制台
        printImageInfo(newInput[0]);
        // 重新绑定 change 事件
        newInput.on('change', function () {
            handleFileSelection();
        });
        console.log('新文件列表长度:', newFiles.length);
    } else {
        // 如果没有剩余图片，手动清空文件选择框
        $('#imageForm')[0].reset();
        console.log('新文件列表长度:', newFiles.length);
    }
}
function handleFileSelection() {
    // 处理文件选择的逻辑
    var files = $('#imageInput')[0].files;// 获取文件列表
    var previewContainer = $('#previewContainer');// 获取错误信息
    var errorOccurred = false; // 添加错误标志
    // 清空预览容器
    previewContainer.empty();
    // 只遍历前几张张图片
    for (var i = 0; i < Math.min(files.length, 10); i++) {
        var file = files[i];
        // 检查文件类型是否为图片
        if (!file.type.startsWith('image/')) {
            alert('请选择图片文件');
            errorOccurred = true;
            break; // 停止循环
        }
        // 检查文件大小是否超过10MB
        if (file.size > 10 * 1024 * 1024) {
            alert('图片大小不能超过10MB');
            errorOccurred = true;
            break; // 停止循环
        }
        // 打印图片信息到控制台
        printImageInfo(file);
        // 创建 FileReader 图片预览
        var reader = new FileReader();
        // 设置读取完成的回调函数
        reader.onload = (function (file, index) {
            return function (e) {
                // 创建预览图
                var previewImage = $(
                    '<div class="col-md-2">' +
                    '<img src="' + e.target.result + '" class="img-fluid preview-image" alt="预览图片">' +
                    '<button type="button" class="btn btn-danger btn-sm mt-2" onclick="deletePreviewImage(' + index + ')">删除</button>' +
                    '</div>'
                );
                // 将预览图添加到预览容器
                previewContainer.append(previewImage);
            };
        })(file, i);
        // 读取文件数据
        reader.readAsDataURL(file);
    }
    // 如果发生错误，清空预览容器
    if (errorOccurred) {
        previewContainer.empty();
        // 重置文件选择框
        $('#imageForm')[0].reset();
    }
}

// 初始绑定 change 事件
$('#imageForm').on('change', '#imageInput', function () {
    handleFileSelection();
});

// 添加文件数量限制
$('#imageInput').on('change', function () {
    var files = this.files;

    // 如果选择的文件数量超过限制，提示用户并清空文件选择框
    if (files.length > 10) {
        alert('最多只能选择10张图片');
        $('#imageForm')[0].reset(); // 清空文件选择框
    }
});
