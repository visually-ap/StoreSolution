$(function () {
    callValidationFormService(
        $('#paymentForm')
        , {
            paymentDate: validationObject.paymentDate.rules
            , paymentPic: validationObject.paymentPic.rules
            , paymentMemo: validationObject.paymentMemo.rules
        }
        , {
            paymentDate:validationObject.paymentDate.message
            , paymentPic: validationObject.paymentPic.message
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
    $('#paymentAmountView').val('');
    $('#paymentAmount').val('');
    $('#paymentMemo').val('');
}

function setCustomerPaymentInfo(data) {
    $('#paymentId').val(data.paymentId);
    fp1.setDate(data.paymentDate, true);
    $('#paymentType').val(data.type);
    $('#paymentPic').val(data.pic);
    $('#paymentMethod').val(data.method);
    $('#paymentAmountView').val(addComma(data.amount.toString()));
    $('#paymentAmount').val(data.amount);
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
                setCustomerPaymentInfo(response.result);
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

$(document).on('focus', '#paymentAmountView', function () {
    const inputtedValue = $(this).val();
    $(this).val(inputtedValue.replaceAll(',', ''));
});

$(document).on('blur', '#paymentAmountView', function () {
    const inputValue = $(this).val();
    if (isEmpty(inputValue)) {
        return;
    }

    if (!isValid('^[0-9]{1,8}$', inputValue)) {
        alert($(this).data('msg'));
        $(this).val('')
        $(this).focus();
        return;
    }

    $($(this).data('target')).val(inputValue);
    $(this).val(addComma(inputValue));
});
