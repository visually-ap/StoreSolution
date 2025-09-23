function initPantsGaugeResultSize() {
    $('#pantsSizeGaugeWaist').val('');
    $('#pantsSizeGaugeHip').val('');
    $('#pantsSizeGaugeThigh').val('');
    $('#pantsSizeGaugeRise').val('');
    $('#pantsSizeGaugeInseam').val('');
    $('#pantsSizeGaugeNee').val('');
    $('#pantsSizeGaugeOpening').val('');
    $('#pantsSizeGaugeGarmentLength').val('');

    $('#pantsSizeResultWaist').val('');
    $('#pantsSizeResultHip').val('');
    $('#pantsSizeResultThigh').val('');
    $('#pantsSizeResultRise').val('');
    $('#pantsSizeResultInseam').val('');
    $('#pantsSizeResultNee').val('');
    $('#pantsSizeResultOpening').val('');
    $('#pantsSizeResultGarmentLength').val('');
}

function setReadonlyPantsSizeEditInput(state) {
    $('.pantsSizeEditInput').prop('readonly', state);
    setReadonlySelect($('.pantsSizeEditSelect'), state);
}

function setPantsGaugeSize(data) {
    $('#pantsSizeGaugeWaist').val(data.data01.toFixed(1));
    $('#pantsSizeGaugeHip').val(data.data02.toFixed(1));
    $('#pantsSizeGaugeThigh').val(data.data03.toFixed(1));
    $('#pantsSizeGaugeRise').val(data.data04.toFixed(1));
    $('#pantsSizeGaugeInseam').val(data.data05.toFixed(1));
    $('#pantsSizeGaugeNee').val(data.data06.toFixed(1));
    $('#pantsSizeGaugeOpening').val(data.data07.toFixed(1));
    $('#pantsSizeGaugeGarmentLength').val(data.data08.toFixed(1));
}

function setPantsResultSize() {
    setSizeEdit($('#pantsSizeEditWaist'));
    setSizeEdit($('#pantsSizeEditHip'));
    setThigh();
    setSizeEdit($('#pantsSizeEditRise'));
    setSizeEdit($('#pantsSizeEditInseam'));
    setSizeEdit($('#pantsSizeEditNee'));
    setSizeEdit($('#pantsSizeEditOpening'));
    setSizeEdit($('#pantsSizeEditGarmentLength'));
}

function setPantsDetailSize() {
    $('.pantsSizeEditDetail').each(function () {
        setSign($(this));
    });
}

function setPantsDetailSizeEdit($obj) {
    const $frontDetail = $obj.parent().next().next().find('input');
    const $backDetail = $obj.parent().next().next().next().find('input');

    const inputValue = $obj.val();
    if (isEmpty(inputValue)) {
        $frontDetail.val('');
        $backDetail.val('');
        return;
    }

    let result = (Number(inputValue) / 2).toFixed(2);

    result = getDecimalPoint(result, 2)
    $frontDetail.val(result);
    setSign($frontDetail);

    $backDetail.val(result);
    setSign($backDetail);
}

function setThigh() {
    // 앞상 - 앞판꼬리
    let detailFrontRise = Number($('#pantsSizeDetailFrontRise').val()) / 2;
    // 뒷상 - 뒷판꼬리
    let detailFrontInseam = Number($('#pantsSizeDetailFrontInseam').val()) / 2;

    let inputValue = $('#pantsSizeEditThigh').val();

    let gaugeData = $('#pantsSizeGaugeThigh').val();

    let result = Number(gaugeData) + Number(inputValue) + Number(detailFrontRise) + Number(detailFrontInseam);

    $('#pantsSizeGaugeThigh').parent().next().next().find('input').val(result == 0 ? '' : result.toFixed(1));

    $('#pantsSizeEditThigh').val(getDecimalPoint(inputValue, 1));
    setSign($('#pantsSizeEditThigh'));
}




// 사이즈 수정 이벤트들
$(document).on('focus', '.pantsSizeEditInput', function() {
    $(this).removeClass('positive_number_color');
    $(this).removeClass('negative_number_color');
    $(this).val($(this).val().replaceAll('+', ''));
    $(this).select();
});

// 허리, 힙, 무릎, 부리 수정량
$(document).on('blur', '#pantsSizeEditWaist, #pantsSizeEditHip, #pantsSizeEditNee, #pantsSizeEditOpening', function() {
    const inputValue = $(this).val();
    if (isEmpty(inputValue) || Number(inputValue) == 0) {
        $(this).val('');
    } else {
        if (!isValid(`^[+-]?[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
            alert(`부호를 포함한 2자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 20.1, -20)`);
            $(this).val('');
            $(this).focus();
            return;
        }
    }

    // 공통 수정량, 완성 계산
    setSizeEdit($(this));

    // 앞판, 뒷판 계산
    setPantsDetailSizeEdit($(this));
});

// 허벅 수정량
$(document).on('blur', '#pantsSizeEditThigh', function() {
    const inputValue = $(this).val();
    if (isEmpty(inputValue) || Number(inputValue) == 0) {
        $(this).val('');
    } else {
        if (!isValid(`^[+-]?[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
            alert(`부호를 포함한 2자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 20.1, -20)`);
            $(this).val('');
            $(this).focus();
            return;
        }
    }

    if (Number(inputValue) > 3) {
        alert("3cm를 초과하여 허벅 추가 늘림을 원할 시 게이지복 사이즈를 증가해 주세요.");
        $(this).val('');
        $(this).focus();
        return;
    }

    // 허벅 수정량, 완성 계산
    setThigh();

    // 앞판, 뒷판 계산
    setPantsDetailSizeEdit($(this));
});

// 앞상, 뒷상, 총기장
$(document).on('blur', '#pantsSizeEditRise, #pantsSizeEditInseam', function() {
    const inputValue = $(this).val();
    if (isEmpty(inputValue) || Number(inputValue) == 0) {
        $(this).val('');
    } else {
        if (!isValid(`^[+-]?[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
            alert(`부호를 포함한 2자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 20.1, -20)`);
            $(this).val('');
            $(this).focus();
            return;
        }
    }

    if (Number(inputValue) > 3) {
        alert("3cm를 초과하여 허벅 추가 늘림을 원할 시 게이지복 사이즈를 증가해 주세요.");
        $(this).val('');
        $(this).focus();
        return;
    }

    // 공통 수정량, 완성 계산
    setSizeEdit($(this));
});

// 총기장
$(document).on('blur', '#pantsSizeEditGarmentLength', function() {
    const inputValue = $(this).val();
    if (isEmpty(inputValue) || Number(inputValue) == 0) {
        $(this).val('');
    } else {
        if (!isValid(`^[+-]?[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
            alert(`부호를 포함한 2자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 20.1, -20)`);
            $(this).val('');
            $(this).focus();
            return;
        }
    }

    // 공통 수정량, 완성 계산
    setSizeEdit($(this));
});

$(document).on('change', '#pantsSizeDetailFrontRise, #pantsSizeDetailFrontInseam', function() {
    setThigh();
});
