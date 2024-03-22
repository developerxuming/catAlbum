
// 获取图床整体信息
function fetchProfile() {
    // Construct the URL
    const apiUrl = 'https://wmimg.com/api/v1/profile'
    // Make the AJAX request
    $.ajax({
        url: apiUrl,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer 229|T38cCckU3TwSdeBZnNWhE0pmXNqIvDC5vtWNT3q9',
            'Accept': 'application/json'
        },
        success: function(response) {
            // console.log('Response Data:', response);
            $("#albumNum").text(response['data'].album_num)  // 显示相册数量
            $("#imageNum").text(response['data'].image_num)  // 显示相册图片数量
            let sizeInMB = response['data']['size'] / 1024; // 将大小转换为MB
            let integerPart = Math.floor(sizeInMB); // 获取整数部分
            $("#useSpace").text(integerPart + 'MB');  // 显示图床已经使用空间
            $("#totalSpace").text(response['data'].capacity / 1024 + 'MB')  // 显示图床总空间
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error('Error:', error);
        }
    });
}

// 获取图床相册信息
function fetchAlbum() {
    // Construct the URL
    const apiUrl = 'https://wmimg.com/api/v1/albums'
    // Make the AJAX request
    $.ajax({
        url: apiUrl,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer 229|T38cCckU3TwSdeBZnNWhE0pmXNqIvDC5vtWNT3q9',
            'Accept': 'application/json'
        },
        success: function(response) {
            // Handle successful response
            // console.log('Response Data:', response);
            showAlbumMessage(response['data']['data'])
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error('Error:', error);
        }
    });
}

// 展示相册信息和修复内容
function showAlbumMessage(albumList) {
    console.log(albumList)
    let innerHTML =
        '<div class="mb-3">' +
        '<h3 class="mb-0">相册信息</h3>' +
        '</div>';
    innerHTML +=
        '<table class="table table-bordered">' +
        '<thead>' + '<tr>' + '<th>排序</th>' + '<th>名称</th>' + '<th>id</th>' + '<th>图片数量</th>' + '<th>介绍</th>' + '<th>编辑</th>' + '</tr>' +
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

// 弹出确认删除的对话框
function deleteConfirm(id, name, num) {
    
    let numConfirm
    if (num === 0){
        numConfirm = "该相册中已无图片"
    } else {
        numConfirm = "该相册中还有" + num + "张图片"
    }
    if (confirm('确认要删除id为' + id+ "，相册名为 " + name + '的相册吗？' + numConfirm)) {
        // 用户点击了"确定"按钮
        deleteAlbum(id, name);
    }
}

// 删除相册
function deleteAlbum(id,name) {
    // 构建URL
    const apiUrl = 'https://wmimg.com/api/v1/albums/'+id
    console.log(apiUrl)
    // 发起AJAX请求
    $.ajax({
        url: apiUrl,
        type: 'DELETE',
        headers: {
            'Authorization': 'Bearer 229|T38cCckU3TwSdeBZnNWhE0pmXNqIvDC5vtWNT3q9',
            'Accept': 'application/json'
        },
        success: function(response) {
            // 处理成功响应
            // console.log('删除成功:', response);
            alert('相册id为'+ id +'的相册删除成功！');
            fetchAlbum()
        },
        error: function(xhr, status, error) {
            // 处理错误
            console.error('删除失败:', error);
        }
    });
}

// 获取图片
function fetchImage(id, name) {
    // Construct the URL
    const apiUrl = 'https://wmimg.com/api/v1/images'
    let requestData = {}
    if (id !== 0) {
        requestData.album_id = id
    }
    // Make the AJAX request
    $.ajax({
        url: apiUrl,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer 229|T38cCckU3TwSdeBZnNWhE0pmXNqIvDC5vtWNT3q9',
            'Accept': 'application/json'
        },
        data: requestData,
        success: function(response) {
            // Handle successful response
            showImage(response['data'], id, name)
            console.log('Response Data:', response);
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error('Error:', error);
        }
    });
}

// 展示图片
function showImage(data, id, name) {
    let current_page = data['current_page']
    let last_page = data['last_page']
    let total = data['total']
    let per_page = data['per_page']
    let innerHTML =
        '<div class="mb-3 row">' +
            '<div class="col">' +
                '<h3 class="mb-0">相册: ${name} 的图片</h3></div>' +
            '<div class="col-auto">' +
                '<button class="btn btn-primary btn-dark">上传图片</button></div></div>'
    innerHTML +=
        '<table class="table table-striped">' +
        '<thead>' + '<tr>' + '<th>序号</th>' + '<th>图片</th>' + '<th>上传时间</th>' + '<th>名称</th>' + '<th>图片大小</th>' + '<th>图片尺寸</th>' + '<th>编辑</th>' + '</tr>' +
        '</thead>' + '<tbody>';
    for (let i = 0; i<data['data'].length; i++) {
        innerHTML += '<tr>' + '<td>' + (i+1+((current_page-1)*per_page)) + '</td>' +
            '<td><img src="' + data['data'][i]['links']['thumbnail_url'] + '" alt="" width="100"></td>' +
            '<td>' + data['data'][i]['date'] + '</td>' +
            '<td>' + data['data'][i]['name'] + '</td>' +
            '<td>' + data['data'][i]['size'] + 'KB</td>' +
            '<td>' + data['data'][i]['height'] + 'x' + data['data'][i]['width'] + '</td>' +
            '<td><button class="btn btn-primary btn-danger btn-sm" onclick="deleteImageConfirm(' + (i+1+((current_page-1)*per_page)) + ', \'' +data['data'][i]['name']+
            '\', \'' + data['data'][i]['key'] + '\', ' + id + ')">删除图片</button></td></tr>'
    }
    innerHTML += '</tbody>' +
        '</table><hr>';
    innerHTML += '<div class="row">' +
        '<button class="btn btn-primary" id="FirstPage" onclick="fetchImage_url(\'' + data['first_page_url'] + '\', ' + id + ', \'' + name + '\')">首页</button>' +
        '<button class="btn btn-primary" id="prevPage" onclick="fetchImage_url(\'' + data['prev_page_url'] + '\', ' + id + ', \'' + name + '\')">上一页</button>' +
        '<span id="pageNumberDisplay" class="mx-3"></span>' +
        '<button class="btn btn-primary" id="NextPage" onclick="fetchImage_url(\'' + data['next_page_url'] + '\', ' + id + ', \'' + name + '\')">下一页</button>' +
        '<button class="btn btn-primary" id="EndPage" onclick="fetchImage_url(\'' + data['last_page_url'] + '\', ' + id + ', \'' + name + '\')">尾页</button>'
    $('#message').html(innerHTML);
    renewButton(current_page, last_page)
}

function deleteImageConfirm(num, name, key, id) {
    // 弹出确认删除的对话框
    if (confirm('确认要删除第' + num+ "张，图片名为 " + name + '的相册吗？')) {
        // 用户点击了"确定"按钮
        deleteImage(key, id, name);
    }
}

// 删除图片
function deleteImage(key, id, name) {
    // 构建URL
    const apiUrl = 'https://wmimg.com/api/v1/images/'+key
    console.log(apiUrl)
    // 发起AJAX请求
    $.ajax({
        url: apiUrl,
        type: 'DELETE',
        headers: {
            'Authorization': 'Bearer 229|T38cCckU3TwSdeBZnNWhE0pmXNqIvDC5vtWNT3q9',
            'Accept': 'application/json'
        },
        success: function(response) {
            // 处理成功响应
            // console.log('删除成功:', response);
            alert('图片删除成功！');
            fetchImage(id,name)
        },
        error: function(xhr, status, error) {
            // 处理错误
            console.error('删除失败:', error);
        }
    });
}

// 更新按钮可点击状态
function renewButton(current_page, last_page) {
    if (current_page === 1) {
        $('#FirstPage, #prevPage').prop('disabled', true)
    } else {
        $('#FirstPage, #prevPage').prop('disabled', false)
    }
    if (current_page === last_page) {
        $('#nextPage, #EndPage').prop('disabled', true)
    } else {
        $('#nextPage, #EndPage').prop('disabled', false)
    }
    $('#pageNumberDisplay').text(current_page + '/' + last_page);
}

// 通过url获取图片
function fetchImage_url(url, id, name) {
    // Construct the URL
    const apiUrl = url
    // Make the AJAX request
    $.ajax({
        url: apiUrl,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer 229|T38cCckU3TwSdeBZnNWhE0pmXNqIvDC5vtWNT3q9',
            'Accept': 'application/json'
        },
        success: function(response) {
            // Handle successful response
            showImage(response['data'], id, name)
            console.log('Response Data:', response);
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error('Error:', error);
        }
    });
}