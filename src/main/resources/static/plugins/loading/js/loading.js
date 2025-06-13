$(document).ready(function () {
    $('body').append("<div id='loading'></div>")
    $('#loading').append("<img id='loadingImg' src='/plugins/loading/images/loading.gif'>");
    $('#loading').hide(); //첫 시작시 로딩바를 숨겨준다.
});

// $(document).ajaxStart(function () {
//     $('#loadingImg').css({
//         'top' : (((window.innerHeight || document.documentElement.clientHeight) - $('#loadingImg').outerHeight()) / 2) + 'px'
//         ,'left': (((window.innerWidth || document.documentElement.clientWidth) - $('#loadingImg').outerWidth()) / 2) + 'px'
//     });
//     $('#loading').show();
// });

$(document).ajaxStop(function () {
    $('#loading').hide(); //ajax종료시 로딩바를 숨겨준다.
});

function showLoadingImage() {
    $('#loadingImg').css({
        'top' : (((window.innerHeight || document.documentElement.clientHeight) - $('#loadingImg').outerHeight()) / 2) + 'px'
        ,'left': (((window.innerWidth || document.documentElement.clientWidth) - $('#loadingImg').outerWidth()) / 2) + 'px'
    });
    $('#loading').show();
}
