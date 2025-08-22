function openModal(element, callback) {
    if ($(element).is(':visible')) {
        return;
    }

    $('body').css('overflow-y', 'hidden');
    $(element).css({"display": "block"});

    callback();
}

function closeModal(element, callback) {
    if ($(element).is(':hidden')) {
        return;
    }

    $('body').css('overflow-y', 'scroll');
    $(element).css({"display": "none"});

    callback();
}

// 공통 헤더 검색창
$(document).ready(function () {
    $(".search_icon").click(function () {
        $(".search_modal_wrap").fadeIn();
    });

    $(".close_btn").click(function () {
        $(".search_modal_wrap").fadeOut();
    });
})
