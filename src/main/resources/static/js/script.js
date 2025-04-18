$(document).ready(function() {
    // Đóng modal khi nhấn vào nút đóng
    $('.close').click(function() {
        $(this).closest('.modal').hide();
    });

    // Mở modal khi nhấn vào nút "View Details"
    $(document).ready(function() {
        $('#productDetail').on('click',function(e) {
            //var productId = $(this).data('id');
            //loadProductDetails(productId);
            e.preventDefault();
            $('#Body_Modal').modal('show').find('.modal-content').load($(this).attr('action'));
        });
    })


    // Hàm tải chi tiết sản phẩm bằng AJAX
    function loadProductDetails(productId) {
        $.ajax({
            url: '/SHOP/Detail/' + productId,
            type: 'GET',
            success: function(result) {
                //var detailsHtml = `<h2>${product.name}</h2><p>${product.description}</p>`;
                $('.Body_Modal').html(result);
            },
            error: function() {
                alert('Error loading product details.');
            }
        });
    }
});
