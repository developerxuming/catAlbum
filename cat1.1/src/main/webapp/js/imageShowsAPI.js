let from  // 读取该页面的第一章图片的序号
let to  // 读取该页面最后一张的图片序号
let total  // 读取该相册图片总数
let current_page   // 读取当前页码
let first_page_url  // 首页url
let last_page   // 尾页页码
let last_page_url  // 尾页url
let next_page_url  // 下一页url
let prev_page_url  // 当前页url
let album;  // 相册id

let urlParams = new URLSearchParams(window.location.search)
fetchAllAlbum()
// 用于初始页面，当进入该页面时，检查url里是否存在参数，如果存在参数则按已有的参数读取图片
if (urlParams.has("externalAlbumId")) {  // 如果urlParams有参数
    let externalAlbumId = urlParams.get('externalAlbumId')
    let externalPage = urlParams.get('externalPage')
    let externalOrder = urlParams.get('externalOrder')
    console.log(externalAlbumId)
    console.log(externalPage)
    console.log(externalOrder)
    console.log("获取到参数")
    let respose = fetchImageData(externalAlbumId, externalPage, externalOrder)
} else {
    console.log("未检测到输入参数")
    fetchImageData(41, 0, 'default')
}

function fetchImageData(albumId, page, order) {
    // Construct the URL
    album = albumId
    let apiUrl = 'https://wmimg.com/api/v1/images';
    let requestData = {}
    if (page !== 0) {
        requestData.page = page
    }
    if (albumId !== 0) {
        requestData.album_id = albumId
    }
    if (order !== "default") {
        requestData.order = order
    }

    // Make the AJAX request
    $.ajax({
        url: apiUrl,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer 95|6nk2pFUcl4P2I36kOblyP1Y3obTP8Sv8dWBVrKeR',
            'Accept': 'application/json'
        },
        data: requestData,
        success: function(response) {
            // Handle successful response
            console.log('Response Data:', response);
            readBase(response.data)
            console.log(response.data)
            readImage(response.data.data)
            renewButton()
            return response
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error('Error:', error);
        }
    });
}

function readBase(data) {  // 读相册基础数据
    from = data["from"]; // 读取该页面的第一章图片的序号
    to = data["to"]; // 读取该页面最后一张的图片序号
    total = data["total"]; // 读取该相册图片总数
    perpage = data.per_page // 读取每页图片默认数量
    current_page = data.current_page;  // 读取当前页码
    console.log('当前页面是第' + current_page + '页')
    first_page_url = data.first_page_url; // 首页url
    last_page = data.last_page;  // 尾页页码
    console.log('最后一页是' + last_page + '页')
    last_page_url = data.last_page_url; // 尾页url
    next_page_url = data.next_page_url; // 下一页url
    prev_page_url= data.prev_page_url; // 当前页url
}

function fetchImageData_url(url) {
    // Construct the URL
    let apiUrl = url;

    // Make the AJAX request
    $.ajax({
        url: apiUrl,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer 95|6nk2pFUcl4P2I36kOblyP1Y3obTP8Sv8dWBVrKeR',
            'Accept': 'application/json'
        },
        success: function(response) {
            // Handle successful response
            console.log('Response Data:', response);
            readBase(response.data)
            console.log(response.data)
            readImage(response.data.data)
            renewButton()
            return response
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error('Error:', error);
        }
    });
}
function readImage(data) {
    // 获取图片容器
    var imageContainer = document.getElementById('imageContainer');
    // 清空原有的图片显示区域
    imageContainer.innerHTML = '';
    // 遍历图片数据，并创建图片元素
    for (let i = 0; i < data.length; i++) {
        var image = data[i];
        // console.log(image.links.thumbnail_url)
        var imgElement =
            // '<div class="shuffle-wrapper">' +
            "<div class=\"portfolio-item shuffle-item grid-item\">" +
            "<img src=\""+ image.links.thumbnail_url +'" alt="">' +
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

function renewButton() {
    if(current_page === 1) {
        $('#FirstPage, #prevPage').prop('disabled', true)
    } else {
        $('#FirstPage, #prevPage').prop('disabled', false)
    }
    if(current_page === last_page) {
        $('#nextPage, #EndPage').prop('disabled', true)
    } else {
        $('#nextPage, #EndPage').prop('disabled', false)
    }
    $('#pageNumberDisplay').text(current_page + '/' + last_page);
}
function loadName() {
    // 直接跳转到目标页面
    window.location.href = "catName";
}

$(document).ready(function () {
    // 首页按钮
    $('#FirstPage').click(function(){
        let response = fetchImageData(album, 1, 'default')
    });
    // 上一页按钮
    $('#prevPage').click(function(){
        let response = fetchImageData_url(prev_page_url)
    });
    // 下一页按钮
    $('#nextPage').click(function(){
        let response = fetchImageData_url(next_page_url)

    });
    // 尾页按钮
    $('#EndPage').click(function(){
        let response = fetchImageData_url(last_page_url)
    });

    $("#loadname").on("click", function () {
        loadName();
    });
});

function fetchAllAlbum() { // 获取所有相册名字
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
            console.log('Response Data:', response);
            processAlbum(response['data']['data'])
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error('Error:', error);
        }
    });
}

function processAlbum(data) {
// 获取容器
    let southContainer = document.getElementById('southContainer');
    let northContainer = document.getElementById('northContainer');
    let adoptContainer = document.getElementById('adoptContainer');
    // 清空原有的图片显示区域
    southContainer.innerHTML = '';
    northContainer.innerHTML = '';
    adoptContainer.innerHTML = '';
    // 遍历图片数据，并创建图片元素
    // southContainer.innerHTML = '<li><a className="link_name" href="#">South</a></li> '
    // northContainer.innerHTML = '<li><a className="link_name" href="#">North</a></li> '
    // adoptContainer.innerHTML = '<li><a className="link_name" href="#">Adopt</a></li> '
    for (let i = 0; i < data.length; i++) {
        let Element = '<li><a href="#" onclick="fetchImageData('+ data[i]['id'] +',0,\'default\')">'+data[i]['name'] +'</a></li>'
        if (data[i]['intro'] === "南区") {
            southContainer.innerHTML += Element;
        } else if (data[i]['intro'] === "北区") {
            northContainer.innerHTML += Element;
        } else {
            adoptContainer.innerHTML += Element;
        }
    }
}
