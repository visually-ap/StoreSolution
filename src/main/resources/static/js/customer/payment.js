$(function () {
    callValidationFormService(
        $('#paymentForm')
        , {
            paymentDate: validationObject.paymentDate.rules
            , paymentPic: validationObject.paymentPic.rules
            , paymentAmount: validationObject.paymentAmount.rules
            , paymentOutstanding: validationObject.paymentOutstanding.rules
            , paymentMemo: validationObject.paymentMemo.rules
        }
        , {
            paymentDate:validationObject.paymentDate.message
            , paymentPic: validationObject.paymentPic.message
            , paymentAmount: validationObject.paymentAmount.message
            , paymentOutstanding: validationObject.paymentOutstanding.message
            , paymentMemo: validationObject.paymentMemo.message
        }
        , function (form) {
            if (doubleClick.isDouble()) {
                return false;
            }

            if (!confirm($(form).data('message'))) {
                doubleClick.initFlag();
                return;
            }

            showLoadingImage();

            callService(
                "POST"
                , $(form).data('url')
                , JSON.stringify(convertFormToJSON(form))
                , function (response) {
                    alert(response.message);
                    location.reload();
                }
            );
        }
    );
});

function initCustomerPaymentInfo() {
    $('#paymentForm').attr('data-url', '');
    $('#paymentForm').attr('data-message', '');
    $('#paymentId').val('');
    fp1.clear();
    $('#paymentType').val(0);
    $('#paymentPic').val('');
    $('#paymentMethod').val(0);
    $('#paymentAmount').val('');
    $('#paymentOutstanding').val('');
    $('#paymentMemo').val('');
}

function setCustomerPaymentInfo(data) {
    $('#paymentId').val(data.paymentId);
    fp1.setDate(data.paymentDate, true);
    $('#paymentType').val(data.type);
    $('#paymentPic').val(data.pic);
    $('#paymentMethod').val(data.method);
    $('#paymentAmount').val(data.amount);
    $('#paymentOutstanding').val(data.outstanding);
    $('#paymentMemo').val(data.memo);
}

$(document).on('click', '.paymentDetail', function() {
    if (doubleClick.isDouble()) {
        return false;
    }

    callLazyService(
        "GET"
        , `/reservation/customer/payment/detail?paymentId=${$(this).data('paymentid')}`
        , {}
        , function (response) {
            if (response.success) {
                openModal('#paymentModal', initCustomerPaymentInfo);
                setCustomerPaymentInfo(response.result)
                $('#paymentModalRegisterButton').hide();
                $('#paymentModalUpdateButton').show();
            } else {
                alert(response.message);
            }
        }
    );
});

$(document).on('click', '#paymentRegisterModalOpenButton', function() {
    openModal('#paymentModal', initCustomerPaymentInfo);
    $('#paymentModalRegisterButton').show();
    $('#paymentModalUpdateButton').hide();
});

$(document).on('click', '#paymentModalRegisterButton', function() {
    $('#paymentForm').attr('data-url', '/reservation/customer/payment/register');
    $('#paymentForm').attr('data-message', '등록하시겠습니까?');
    $('#paymentForm').submit();
});

$(document).on('click', '#paymentModalUpdateButton', function() {
    $('#paymentForm').attr('data-url', '/reservation/customer/payment/update');
    $('#paymentForm').attr('data-message', '수정하시겠습니까?');
    $('#paymentForm').submit();
});

$(document).on('click', '.paymentDeleteButton', function (e) {
    e.stopPropagation();

    if (doubleClick.isDouble()) {
        return false;
    }

    if (!confirm("삭제하시겠습니까?")) {
        doubleClick.initFlag();
        return;
    }

    showLoadingImage();

    callLazyService(
        "POST"
        , `/reservation/customer/payment/delete?paymentId=${$(this).parents('tr').data('paymentid')}`
        , {}
        , function (response) {
            alert(response.message);
            location.reload();
        }
    );
});
