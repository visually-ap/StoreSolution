// 공통 헤더 검색창
$(document).ready(function () {
    $(".right_area .search_icon").click(function () {
        $(".search_modal_wrap").fadeIn();
    });

    $(".close_btn").click(function () {
        $(".search_modal_wrap").fadeOut();
    });
})