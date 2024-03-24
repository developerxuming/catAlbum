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
            buildTabs(response['data']['data'])
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error('Error:', error);
        }
    });
}

function buildTabs(data) {
    
}