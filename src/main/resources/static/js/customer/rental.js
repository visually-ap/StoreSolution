let isSearching = false;
let isSearchItem = false;

$(function () {
    callValidationFormService(
        $('#rentalForm')
        , {
            rentalFromDate: validationObject.rentalFromDate.rules
            , rentalRequestDate: validationObject.rentalRequestDate.rules
            , rentalMemo: validationObject.rentalMemo.rules
        }
        , {
            rentalFromDate: validationObject.rentalFromDate.message
            , rentalRequestDate: validationObject.rentalRequestDate.message
            , rentalMemo: validationObject.rentalMemo.message
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

function hideRentalModalElements() {
    $('#rentingStateWrap').hide();
    $('#rentalItemSearchResultWrap').hide();
    $('#rentalItemToDateWrap').hide();
    $('#rentalModalRegisterButton').hide();
    $('#rentalModalUpdateButton').hide();
    $('#rentalItemSearchButton').hide();
    $('#rentalModalRentalCompleteButton').hide();
    $('#rentalModalRentalCancelButton').hide();
    $('#rentalModalBackCompleteButton').hide();
    $('#rentalModalBackCancelButton').hide();
}

function renderRentableItemList(rentalItemList) {
    $('#rentalItemListBody').empty();
    if (rentalItemList.length == 0) {
        $('#rentalItemListBody').append(`
                        <tr>
                            <td colspan="3">대여 가능한 대여복이 존재하지 않습니다.</td>
                        </tr>
                        `);
    } else {
        rentalItemList.forEach(function (rentalItem) {
            $('#rentalItemListBody').append(`
                            <tr class="selectRental" data-rentalid="${rentalItem.rentalItemId}" data-rentalitem="${rentalItem.name}">
                                <td>${rentalItem.name}</td>
                                <td>${rentalItem.size}</td>
                                <td>${rentalItem.memo == null ? '' : rentalItem.memo}</td>
                            </tr>
                            `);
        });
    }
    $('#rentalItemSearchResultWrap').show();
}

function setCustomerRentalInfo(data) {
    $('#rentingStateWrap').text(` (${data.rentingState})`);
    $('#rentalId').val(data.rentalId);
    $('#rentalType').val(data.type);

    fp2.setDate(data.fromDate, true);
    fp3.setDate(data.requestDate, true);

    if (data.toDate == null) {
        fp4.clear();
    } else {
        fp4.setDate(data.toDate, true);
    }
    $('#rentalItemName').val(data.rentalItemName);
    $('#rentalItemId').val(data.rentalItemId);
    $('#rentalMemo').val(data.memo);
    $('#rentalItemListBody').empty();
}

function initCustomerRentalInfo() {
    $('#rentalForm').attr('data-url', '');
    $('#rentalForm').attr('data-message', '');
    $('#rentingStateWrap').text(``);
    $('#rentalId').val('');
    $('#rentalType').val(0);
    fp2.clear();
    fp3.clear();
    $('#rentalItemName').val('');
    $('#rentalItemName').prop('readonly', false);
    $('#rentalItemId').val('');
    $('#rentalMemo').val('');
    $('#rentalItemListBody').empty();
    isSearching = false;
    isSearchItem = false;

    hideRentalModalElements();
}

$(document).on('keydown', '#rentalItemName', function (event) {
    isSearchItem = false;

    if (event.key === 'Enter') {
        event.preventDefault();

        if (isSearching) return;
        isSearching = true;

        $('#rentalItemSearchButton').trigger('click');

        // 500ms 뒤에 다시 허용
        setTimeout(() => isSearching = false, 500);
    }
});

$(document).on('change', '#fromDate, #requestDate', function () {
    isSearchItem = false;
});

$(document).on('click', '#rentalItemSearchButton', function () {
    if (doubleClick.isDouble()) {
        return false;
    }

    if (isEmpty($('#fromDate').val()) || isEmpty($('#requestDate').val())) {
        alert('대여일과 반납예정일을 선택 후 검색이 가능합니다.');
        doubleClick.initFlag();
        return;
    }

    callLazyService(
        "GET"
        , `/reservation/rental/itemList?fromDate=${$('#fromDate').val()}&requestDate=${$('#requestDate').val()}&searchKeyword=${$('#rentalItemName').val()}&rentalId=${$('#rentalId').val()}`
        , {}
        , function (response) {
            if (response.success) {
                renderRentableItemList(response.result);
            } else {
                alert(response.message);
            }
        }
    );
});

$(document).on('click', '.selectRental', function () {
    $('#rentalItemName').val($(this).data('rentalitem'));
    $('#rentalItemId').val($(this).data('rentalid'));
    isSearchItem = true;
    $('#rentalItemListBody').empty();
    $('#rentalItemSearchResultWrap').hide();
});

$(document).on('click', '#rentalRegisterModalOpenButton', function () {
    openModal('#rentalModal', initCustomerRentalInfo);
    $('#rentalItemSearchButton').show();
    $('#rentalModalRegisterButton').show();
});

$(document).on('click', '#rentalModalRegisterButton', function () {
    if (!isSearchItem) {
        alert('대여복을 검색하여 검색 결과에서 대여할 대여복을 선택 후 등록할 수 있습니다.');
        return;
    }

    $('#rentalForm').attr('data-url', '/reservation/customer/rental/register');
    $('#rentalForm').attr('data-message', '등록하시겠습니까?');
    $('#rentalForm').submit();
});

$(document).on('click', '#rentalModalUpdateButton', function () {
    if (!isSearchItem) {
        alert('대여복을 검색하여 검색 결과에서 대여할 대여복을 선택 후 수정할 수 있습니다.');
        return;
    }

    $('#rentalForm').attr('data-url', '/reservation/customer/rental/update');
    $('#rentalForm').attr('data-message', '수정하시겠습니까?');
    $('#rentalForm').submit();
});

$(document).on('click', '.rentalDetail', function() {
    if (doubleClick.isDouble()) {
        return false;
    }

    callLazyService(
        "GET"
        , `/reservation/customer/rental/detail?rentalId=${$(this).data('rentalid')}`
        , {}
        , function (response) {
            if (response.success) {
                openModal('#rentalModal', initCustomerRentalInfo);
                setCustomerRentalInfo(response.result);
                $('#rentingStateWrap').show();
                isSearchItem = true;
                if (response.result.rentingStateType == 1) {
                    // 대여 예약 중 -> 대여완료 버튼
                    $('#rentalItemSearchButton').show();
                    $('#rentalModalUpdateButton').show();
                    $('#rentalModalRentalCompleteButton').show();
                } else if (response.result.rentingStateType == 2) {
                    // 대여 중 -> 반납완료, 대여취소 show
                    $('#rentalModalRentalCancelButton').show();
                    $('#rentalModalBackCompleteButton').show();
                } else if (response.result.rentingStateType == 3) {
                    // 반납 완료 -> 반납 취소 버튼, 반납일
                    $('#rentalModalBackCancelButton').show();
                    $('#rentalItemToDateWrap').show();
                }
            } else {
                alert(response.message);
            }
        }
    );
});

$(document).on('click', '#rentalModalRentalCompleteButton', function() {
    if (doubleClick.isDouble()) {
        return false;
    }

    if (!confirm("대여완료 처리를 하시겠습니까?")) {
        doubleClick.initFlag();
        return;
    }

    showLoadingImage();

    callService(
        "POST"
        , `/reservation/customer/rental/complete?rentalId=${$('#rentalId').val()}`
        , {}
        , function (response) {
            alert(response.message);
            location.reload();
        }
    );
});

$(document).on('click', '#rentalModalRentalCancelButton', function() {
    if (doubleClick.isDouble()) {
        return false;
    }

    if (!confirm("대여취소 처리를 하시겠습니까?")) {
        doubleClick.initFlag();
        return;
    }

    showLoadingImage();

    callService(
        "POST"
        , `/reservation/customer/rental/complete/cancel?rentalId=${$('#rentalId').val()}`
        , {}
        , function (response) {
            alert(response.message);
            location.reload();
        }
    );
});

$(document).on('click', '#rentalModalBackCompleteButton', function() {
    if (doubleClick.isDouble()) {
        return false;
    }

    if (!confirm("금일 날짜로 반납완료 처리됩니다.\n반납 날짜는 완료 처리 후 수정할 수 있습니다.\n반납완료 처리를 하시겠습니까?")) {
        doubleClick.initFlag();
        return;
    }

    showLoadingImage();

    callService(
        "POST"
        , `/reservation/customer/rental/back?rentalId=${$('#rentalId').val()}`
        , {}
        , function (response) {
            alert(response.message);
            location.reload();
        }
    );
});

$(document).on('click', '#rentalModalBackCancelButton', function() {
    if (doubleClick.isDouble()) {
        return false;
    }

    if (!confirm("반납 취소 처리를 하시겠습니까?")) {
        doubleClick.initFlag();
        return;
    }

    showLoadingImage();

    callService(
        "POST"
        , `/reservation/customer/rental/back/cancel?rentalId=${$('#rentalId').val()}`
        , {}
        , function (response) {
            alert(response.message);
            location.reload();
        }
    );
});

$(document).on('change', '#toDate', function() {
    if (doubleClick.isDouble()) {
        return false;
    }

    showLoadingImage();

    callService(
        "POST"
        , `/reservation/customer/rental/todate/update?rentalId=${$('#rentalId').val()}&toDate=${$(this).val()}`
        , {}
        , function (response) {
            alert('반납일을 수정하였습니다.');
            location.reload();
        }
    );
});

$(document).on('click', '.rentalDeleteButton', function(e) {
    e.stopPropagation();

    if (doubleClick.isDouble()) {
        return false;
    }

    if (!confirm("삭제 하시겠습니까?")) {
        doubleClick.initFlag();
        return;
    }

    showLoadingImage();

    callService(
        "POST"
        , `/reservation/customer/rental/delete?rentalId=${$(this).parents('tr').data('rentalid')}`
        , {}
        , function (response) {
            alert(response.message);
            location.reload();
        }
    );
});
