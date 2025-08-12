/**
 * 지정된 URL로 ajax 비동기 통신을 한다.
 * @param type      - post, get
 * @param url       - 통신할 URL
 * @param param     - 통신 파라메터
 * @param callback  - 통신 후 콜백 함수
 */
function callService(type, url, param, callback) {
    $.ajax({
        type: type
        , url: url
        , dataType: 'json'
        , contentType: 'application/json'
        , data: param
        , beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        }
        , success: function (data) {
            if (callback != null) {
                return callback(data, url);
            }
        }
        , error: function (request) {
            doubleClick.initFlag();
            if (request.status == '403') {
                alert('로그인이 필요한 서비스입니다.')
                location.reload();
            } else {
                alert('서비스 호출에 실패하였습니다.\n문제가 지속적으로 발생 시 관리자에게 문의하시기 바랍니다.');
            }
        }
        , complete: function () {
            doubleClick.initFlag();
        }
    });
}

function callLazyService(type, url, param, callback) {
    $.ajax({
        type: type
        , url: url
        , async: true
        , dataType: 'json'
        , contentType: 'application/json'
        , data: param
        , beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        }
        , success: function (data) {
            if (callback != null) {
                return callback(data, url);
            }
        }
        , error: function (request) {
            doubleClick.initFlag();
            if (request.status == '403') {
                alert('로그인이 필요한 서비스입니다.')
                location.reload();
            } else {
                alert('서비스 호출에 실패하였습니다.\n문제가 지속적으로 발생 시 관리자에게 문의하시기 바랍니다.');
            }
        }
        , complete: function () {
            doubleClick.initFlag();
        }
    });
}

function callFileAttachService($form, $file, url, callback) {
    if (!$file.val()) {
        $file.attr('disabled', true);
    }

    $form.attr('enctype', 'multipart/form-data');
    $form.attr('method', 'post');

    $form.ajaxForm({
        url: url
        , enctype: 'multipart/form-data'
        , method: "post"
        , beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        }
        , error: function (request) {
            doubleClick.initFlag();
            if (request.status == '403') {
                alert('로그인이 필요한 서비스입니다.');
                location.reload();
            } else {
                alert('서비스 호출에 실패하였습니다.\n문제가 지속적으로 발생 시 관리자에게 문의바랍니다.');
                $file.attr('disabled', false);
            }
        }
        , success: function (data) {
            if (callback != null) {
                return callback(data, url);
            }
        }
        , complete: function () {
            doubleClick.initFlag();
        }
    }).submit();
}

function callMultiFileInputAttachService($form, url, callback) {
    let $files = $form.find('input[type="file"]');
    $files.each(function () {
        if (!$(this).val()) {
            $(this).attr('disabled', true);
        }
    });

    $form.attr('enctype', 'multipart/form-data');
    $form.attr('method', 'post');

    $form.ajaxForm({
        url: url
        , enctype: 'multipart/form-data'
        , method: "post"
        , beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        }
        , error: function (request) {
            doubleClick.initFlag();
            if (request.status == '403') {
                alert('로그인이 필요한 서비스입니다.')
                location.reload();
            } else {
                alert('서비스 호출에 실패하였습니다.\n문제가 지속적으로 발생 시 관리자에게 문의하시기 바랍니다.');
                $files.each(function () {
                    if (!$(this).val()) {
                        $(this).attr('disabled', false);
                    }
                });
            }
        }
        , success: function (data) {
            if (callback != null) {
                return callback(data, url);
            }
        }
        , complete: function () {
            doubleClick.initFlag();
        }
    }).submit();
}

function callServicePromise(type, url, param = {}, isJson = true) {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: type,
            url: url,
            data: isJson ? JSON.stringify(param) : param,
            dataType: 'json',
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            success: function (data) {
                resolve(data);
            },
            error: function (request) {
                doubleClick.initFlag();

                if (request.status === 403) {
                    alert('로그인이 필요한 서비스입니다.');
                    location.reload();
                } else {
                    alert('서비스 호출에 실패하였습니다.\n문제가 지속되면 관리자에게 문의하세요.');
                }

                reject(request);
            },
            complete: function () {
                doubleClick.initFlag();
            }
        });
    });
}

function convertFormToJSON(form) {
    'use strict';
    let result = {};
    let extend = function (i, element) {
        let node = result[element.name];
        if ('undefined' !== typeof node && node !== null) {
            if ($.isArray(node)) {
                node.push(element.value);
            } else {
                result[element.name] = [node, element.value];
            }
        } else {
            result[element.name] = element.value;
        }
    };

    $.each(this.serializeArray(), extend);
    return result;
}
