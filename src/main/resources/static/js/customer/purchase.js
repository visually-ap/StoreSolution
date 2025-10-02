$(function () {
    callValidationFormService(
        $('#purchaseForm')
        , {
            purchaseDate: validationObject.purchaseDate.rules
            , purchaseMemo: validationObject.purchaseMemo.rules
            , purchasePrice: validationObject.purchasePrice.rules
        }
        , {
            purchaseDate:validationObject.purchaseDate.message
            , purchaseMemo: validationObject.purchaseMemo.message
            , purchasePrice: validationObject.purchasePrice.message
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

function initCustomerPurchaseInfo() {
    $('#purchaseForm').attr('data-url', '');
    $('#purchaseForm').attr('data-message', '');
    fp.clear();
    $('#purchaseId').val('');
    $('#purchaseType').val(0);
    $('#purchaseMemo').val('');
    $('#purchasePrice').val('');
}

function setCustomerPurchaseInfo(data) {
    fp.setDate(data.purchaseDate, true);
    $('#purchaseId').val(data.purchaseId);
    $('#purchaseType').val(data.type);
    $('#purchaseMemo').val(data.purchaseMemo);
    $('#purchasePrice').val(data.price);
}

$(document).on('click', '#purchaseRegisterModalOpenButton', function() {
    openModal('#purchaseModal', initCustomerPurchaseInfo);
    $('#purchaseModalRegisterButton').show();
    $('#purchaseModalUpdateButton').hide();
});

$(document).on('click', '.purchaseDetail', function() {
    if (doubleClick.isDouble()) {
        return false;
    }

    callLazyService(
        "GET"
        , `/reservation/customer/purchase/detail?purchaseId=${$(this).data('purchaseid')}`
        , {}
        , function (response) {
            if (response.success) {
                openModal('#purchaseModal', initCustomerPurchaseInfo);
                setCustomerPurchaseInfo(response.result)
                $('#purchaseModalRegisterButton').hide();
                $('#purchaseModalUpdateButton').show();
            } else {
                alert(response.message);
            }
        }
    );
});

$(document).on('click', '#purchaseModalRegisterButton', function() {
    $('#purchaseForm').attr('data-url', '/reservation/customer/purchase/register');
    $('#purchaseForm').attr('data-message', '등록하시겠습니까?');
    $('#purchaseForm').submit();
});

$(document).on('click', '#purchaseModalUpdateButton', function() {
    $('#purchaseForm').attr('data-url', '/reservation/customer/purchase/update');
    $('#purchaseForm').attr('data-message', '수정하시겠습니까?');
    $('#purchaseForm').submit();
});

$(document).on('click', '.purchaseDeleteButton', function (e) {
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
        , `/reservation/customer/purchase/delete?purchaseId=${$(this).parents('tr').data('purchaseid')}`
        , {}
        , function (response) {
            alert(response.message);
            location.reload();
        }
    );
});
