$(function () {
    callValidationFormService(
        $('#reservationForm')
        , {
            consultingManager: validationObject.consultingManager.rules
            , consultingDate: validationObject.consultingDate.rules
            , consultingHour: validationObject.consultingHour.rules
            , consultingMinute: validationObject.consultingMinute.rules
        }
        , {
            consultingManager: validationObject.consultingManager.message
            , consultingDate: validationObject.consultingDate.message
            , consultingHour: validationObject.consultingHour.message
            , consultingMinute: validationObject.consultingMinute.message
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
                    if (response.success) {
                        location.reload();
                    }
                }
            );
        }
    );
});

$(document).on('click', '#reservationModalRegisterButton', function() {
    $('#reservationForm').attr('data-url', '/reservation/customer/reservation/register');
    $('#reservationForm').attr('data-message', '등록하시겠습니까?');
    $('#reservationForm').submit();
});

$(document).on('click', '#reservationModalUpdateButton', function() {
    $('#reservationForm').attr('data-url', '/reservation/customer/reservation/update');
    $('#reservationForm').attr('data-message', '수정하시겠습니까?');
    $('#reservationForm').submit();
});

$(document).on('click', '#reservationRegisterModalOpenButton', function () {
    openModal('#reservationModal', initCustomerReservationInfo);
    $('#reservationModalRegisterButton').show();
});

$(document).on('click', '.reservationDetail', function () {
    if (doubleClick.isDouble()) {
        return;
    }

    callLazyService(
        "GET"
        , `/reservation/customer/reservation/detail?reservationId=${$(this).data('reservationid')}`
        , {}
        , function (response) {
            if (response.success) {
                openModal('#reservationModal', initCustomerReservationInfo);
                $('#reservationModalUpdateButton').show();
                if (response.result.type != 1 && !response.result.completed) {
                    $('#reservationModalCompleteButton').show();
                }
                setCustomerReservationInfo(response.result);
            } else {
                alert(response.message);
            }
        }
    );
});

$(document).on('click', '#isAllday', function () {
    if ($(this).is(':checked')) {
        $('#consultingHour').val('');
        $('#consultingMinute').val('');
        setReadonlySelect($('#consultingHour'), true);
        setReadonlySelect($('#consultingMinute'), true);
    } else {
        setReadonlySelect($('#consultingHour'), false);
        setReadonlySelect($('#consultingMinute'), false);
    }
});

$(document).on('click', '#reservationModalCompleteButton', function () {
    if (doubleClick.isDouble()) {
        return;
    }

    if (!confirm('완료처리 하시겠습니까?')) {
        doubleClick.initFlag();
        return;
    }

    showLoadingImage();

    callService(
        "POST"
        , `/reservation/customer/reservation/complete?reservationId=${$('#reservationId').val()}`
        , {}
        , function (response) {
            alert(response.message);
            if (response.success) {
                location.reload();
            }
        }
    );
});

function initCustomerReservationInfo() {
    $('#rentalForm').attr('data-url', '');
    $('#rentalForm').attr('data-message', '');

    $('#reservationId').val('')
    $('#reservationType').val(1);
    $('#consultingManager').val('');
    fp5.clear();
    $('#isAllday').prop('checked', false);
    $('#consultingHour').val('');
    $('#consultingMinute').val('');
    $('#reservationContract').val(0);
    $('#reservationMemo').val('');

    hideReservationModalElements();
}

function hideReservationModalElements() {
    $('#reservationModalRegisterButton').hide();
    $('#reservationModalUpdateButton').hide();
    $('#reservationModalCompleteButton').hide();
}

function setCustomerReservationInfo(data) {
    $('#reservationId').val(data.reservationId)
    $('#reservationType').val(data.type);
    $('#consultingManager').val(data.consultingManagerId);
    fp5.setDate(data.consultingDate, true);
    $('#isAllday').prop('checked', data.allDay);
    if (data.allDay) {
        $('#consultingHour').val('');
        $('#consultingMinute').val('');
        setReadonlySelect($('#consultingHour'), true);
        setReadonlySelect($('#consultingMinute'), true);
    } else {
        $('#consultingHour').val(data.consultingHour);
        $('#consultingMinute').val(data.consultingMinute);
        setReadonlySelect($('#consultingHour'), false);
        setReadonlySelect($('#consultingMinute'), false);
    }
    $('#reservationContract').val(data.contract);
    $('#reservationMemo').val(data.memo);
}
