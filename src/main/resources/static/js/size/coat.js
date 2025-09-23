function initCoatGaugeResultSize() {
    $('#coatSizeGaugeChest').val('');
    $('#coatSizeGaugeChestWidth').val('');
    $('#coatSizeGaugeUpperSideBody').val('');
    $('#coatSizeGaugeBackWidth').val('');
    $('#coatSizeGaugeWaist').val('');
    $('#coatSizeGaugeWaistFront').val('');
    $('#coatSizeGaugeMiddleSideBody').val('');
    $('#coatSizeGaugeWaistBack').val('');
    $('#coatSizeGaugeShoulder').val('');
    $('#coatSizeGaugeLeftSleeve').val('');
    $('#coatSizeGaugeRightSleeve').val('');
    $('#coatSizeGaugeBackLength').val('');
    $('#coatSizeGaugeFrontLength').val('');

    $('#coatSizeResultChest').val('');
    $('#coatSizeResultChestWidth').val('');
    $('#coatSizeResultUpperSideBody').val('');
    $('#coatSizeResultBackWidth').val('');
    $('#coatSizeResultWaist').val('');
    $('#coatSizeResultWaistFront').val('');
    $('#coatSizeResultMiddleSideBody').val('');
    $('#coatSizeResultWaistBack').val('');
    $('#coatSizeResultShoulder').val('');
    $('#coatSizeResultLeftSleeve').val('');
    $('#coatSizeResultRightSleeve').val('');
    $('#coatSizeResultBackLength').val('');
    $('#coatSizeResultFrontLength').val('');
}

function setReadonlyCoatSizeEditInput(state) {
    $('.coatSizeEditInput').prop('readonly', state);
    setReadonlySelect($('.coatSizeEditSelect'), state);
}

function setCoatGaugeSize(data) {
    $('#coatSizeGaugeChest').val(data.data01.toFixed(1));
    $('#coatSizeGaugeChestWidth').val(data.data02.toFixed(1));
    $('#coatSizeResultUpperSideBody').val(0.0.toFixed(1));
    $('#coatSizeGaugeBackWidth').val(data.data03.toFixed(1));
    $('#coatSizeGaugeWaist').val(data.data04.toFixed(1));
    $('#coatSizeGaugeWaistFront').val(data.data05.toFixed(1));
    $('#coatSizeResultMiddleSideBody').val(0.0.toFixed(1));
    $('#coatSizeGaugeWaistBack').val(data.data06.toFixed(1));
    $('#coatSizeGaugeShoulder').val(data.data07.toFixed(1));
    $('#coatSizeGaugeLeftSleeve').val(data.data08.toFixed(1));
    $('#coatSizeGaugeRightSleeve').val(data.data09.toFixed(1));
    $('#coatSizeGaugeBackLength').val(data.data10.toFixed(1));
    $('#coatSizeGaugeFrontLength').val(data.data11.toFixed(1));
}

function setCoatResultSize() {
    setCoatChest();
    setCoatChestWidth();
    setSizeEdit($('#coatSizeEditUpperSideBody'));
    setSizeEdit($('#coatSizeEditBackWidth'));
    setCoatWaist();
    setCoatWaistFront();
    setSizeEdit($('#coatSizeEditMiddleSideBody'));
    setCoatWaistBack();
    setSizeEdit($('#coatSizeEditShoulder'));
    setSizeEdit($('#coatSizeEditLeftSleeve'));
    setSizeEdit($('#coatSizeEditRightSleeve'));
    setCoatFrontLength();
    setCoatBackLength();
}

// 상동 계산
function setCoatChest() {
    let coatSizeEditChestWidth = Number($('#coatSizeEditChestWidth').val());
    let coatSizeEditUpperSideBody = Number($('#coatSizeEditUpperSideBody').val());
    let coatSizeEditBackWidth = Number($('#coatSizeEditBackWidth').val());
    let coatSizeChestLine = Number($('#coatSizeChestLine option:selected').val());

    // 수정값 계산
    let coatSizeEditChest = coatSizeEditChestWidth + coatSizeEditUpperSideBody + coatSizeEditBackWidth + coatSizeChestLine;
    $('#coatSizeEditChest').val(getDecimalPoint(coatSizeEditChest, 1));

    // 상동 수정량 부호 설정
    setSign($('#coatSizeEditChest'));

    // 완성값 계산
    setResultSize($('#coatSizeEditChest'));
}

// 상앞품 계산
function setCoatChestWidth() {
    let coatSizeGaugeChestWidth = Number($('#coatSizeGaugeChestWidth').val());
    let coatSizeEditChestWidth = Number($('#coatSizeEditChestWidth').val());
    let coatSizeChestLine = Number($('#coatSizeChestLine option:selected').val());

    // 수정량 세팅
    $('#coatSizeEditChestWidth').val(getDecimalPoint(coatSizeEditChestWidth, 1));
    setSign($('#coatSizeEditChestWidth'));

    // 완성값 계산 - 우찌때문에 공용 메소드 사용불가 -> 직접 계산
    let coatSizeResultChestWidth = coatSizeGaugeChestWidth + coatSizeEditChestWidth + coatSizeChestLine;
    $('#coatSizeResultChestWidth').val(getDecimalPoint(coatSizeResultChestWidth, 1));
}

// 중동 계산
function setCoatWaist() {
    let coatSizeEditWaistFront = Number($('#coatSizeEditWaistFront').val());
    let coatSizeEditMiddleSideBody = Number($('#coatSizeEditMiddleSideBody').val());
    let coatSizeEditWaistBack = Number($('#coatSizeEditWaistBack').val());
    let coatSizeChestLine = Number($('#coatSizeChestLine option:selected').val());
    let coatSizeBackCenterShorten = Number($('#coatSizeBackCenterShorten option:selected').val());

    // 수정값 계산
    let coatSizeEditChest = coatSizeEditWaistFront + coatSizeEditMiddleSideBody + coatSizeEditWaistBack + coatSizeChestLine + coatSizeBackCenterShorten;
    $('#coatSizeEditWaist').val(getDecimalPoint(coatSizeEditChest, 1));
    setSign($('#coatSizeEditWaist'));

    // 완성값 세팅
    setResultSize($('#coatSizeEditWaist'));
}

// 중앞품 계산
function setCoatWaistFront() {
    let coatSizeGaugeWaistFront = Number($('#coatSizeGaugeWaistFront').val());
    let coatSizeEditWaistFront = Number($('#coatSizeEditWaistFront').val());
    let coatSizeChestLine = Number($('#coatSizeChestLine option:selected').val());

    // 수정량 세팅
    $('#coatSizeEditWaistFront').val(getDecimalPoint(coatSizeEditWaistFront, 1));
    setSign($('#coatSizeEditWaistFront'));

    // 완성값 계산 - 우찌때문에
    let coatSizeResultWaistFront = coatSizeGaugeWaistFront + coatSizeEditWaistFront + coatSizeChestLine;
    $('#coatSizeResultWaistFront').val(getDecimalPoint(coatSizeResultWaistFront, 1));
}

// 중뒷품 계산
function setCoatWaistBack() {
    let coatSizeGaugeWaistBack = Number($('#coatSizeGaugeWaistBack').val());
    let coatSizeEditWaistBack = Number($('#coatSizeEditWaistBack').val());
    let coatSizeBackCenterShorten = Number($('#coatSizeBackCenterShorten option:selected').val());

    // 수정량 세팅
    $('#coatSizeEditWaistBack').val(getDecimalPoint(coatSizeEditWaistBack, 1));
    setSign($('#coatSizeEditWaistBack'));

    // 완성값 계산 - 뒷중심줄임때문에
    let coatSizeResultWaistBack = coatSizeGaugeWaistBack + coatSizeEditWaistBack + coatSizeBackCenterShorten;
    $('#coatSizeResultWaistBack').val(getDecimalPoint(coatSizeResultWaistBack, 1));
}

// 앞기장 계산
function setCoatFrontLength() {
    let coatSizeFrontRound = Number($('#coatSizeFrontRound option:selected').val());
    let coatSizeBackRound = Number($('#coatSizeBackRound option:selected').val());
    let coatSizeFrontLength = Number($('#coatSizeFrontLength option:selected').val());
    let coatSizeEditBackLength = Number($('#coatSizeEditBackLength').val());

    let resultEditValue = -(coatSizeFrontRound * 2) + (coatSizeBackRound * 2) + coatSizeFrontLength + coatSizeEditBackLength;

    // 수정량 세팅
    $('#coatSizeEditFrontLength').val(getDecimalPoint(resultEditValue, 1));
    setSign($('#coatSizeEditFrontLength'));

    // 완성값 세팅
    setResultSize($('#coatSizeEditFrontLength'));
}

function setCoatBackLength() {
    let coatSizeGaugeBackLength = Number($('#coatSizeGaugeBackLength').val());
    let coatSizeEditBackLength = Number($('#coatSizeEditBackLength').val());
    let backNecklineEditValue = Number($('#coatSizeBackNeckline option:selected').val());

    // 수정량 세팅
    $('#coatSizeEditBackLength').val(getDecimalPoint(coatSizeEditBackLength, 1));
    setSign($('#coatSizeEditBackLength'));

    // 완성값 세팅 - 뒷중심줄임때문에
    let result = Number(coatSizeGaugeBackLength) + Number(coatSizeEditBackLength) + Number(backNecklineEditValue);
    $('#coatSizeResultBackLength').val(getDecimalPoint(result, 1));
}

function initCoatGauge() {
    $("#coatGauge").empty();
    $("#coatGauge").append("<option value='' selected>게이지복</option>");
}

function initCoatPattern() {
    $('#coatPattern').find('option:eq(0)').prop('selected', true);
}




let beforeCoatSize;
$(document).on('focus', '#coatPattern', function () {
    beforeCoatSize = $("option:selected", this).val();
});
$(document).on('change', '#coatPattern', function () {
    if (!isEmpty(beforeCoatSize) && !confirm('코트 패턴 변경 시 게이지복이 초기화됩니다.\n변경 하시겠습니까?')) {
        $('#coatPattern').val(beforeCoatSize);
        return;
    }

    // 게이지복 초기화
    initCoatGauge();

    // 사이즈 입력 초기화
    initCoatGaugeResultSize();
    setReadonlyCoatSizeEditInput(true);

    if (isEmpty($(this).val())) {
        initCoatPattern();
        return;
    } else {
        // 게이지복 세팅
        callLazyService(
            "GET"
            , `/counseling/pattern/gauge?id=${$(this).val()}`
            , {}
            , function (response) {
                coatGaugeList = response.list;
                $.each(coatGaugeList, function (i, x) {
                    $("#coatGauge").append(`<option value='${x.id}'>${x.sizeType}</option>`);
                });
            }
        );
    }
    $(this).blur();
});



// 사이즈 수정 이벤트들
$(document).on('focus', '.coatSizeEditInput', function() {
    $(this).removeClass('positive_number_color');
    $(this).removeClass('negative_number_color');
    $(this).val($(this).val().replaceAll('+', ''));
    $(this).select();
});

// 상앞품 수정량
$(document).on('blur', '#coatSizeEditChestWidth', function() {
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

    // 상동 수정량, 완성 계산
    setCoatChest();

    // 상앞품 수정량, 완성 계산
    setCoatChestWidth();
});

// 상사이바, 상뒷품 수정량
$(document).on('blur', '#coatSizeEditUpperSideBody, #coatSizeEditBackWidth', function() {
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

    // 상동 수정량, 완성 계산
    setCoatChest();

    // 상사이바 수정량, 완성 계산
    setSizeEdit($(this));
});

// 중앞품 수정량
$(document).on('blur', '#coatSizeEditWaistFront', function() {
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

    // 중동 수정량, 완성 계산
    setCoatWaist();

    // 중앞품 수정량, 완성 계산
    setCoatWaistFront();
});

// 중사이바 수정량
$(document).on('blur', '#coatSizeEditMiddleSideBody', function() {
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

    // 중동 수정량, 완성 계산
    setCoatWaist();

    // 중사이바 수정량, 완성 계산
    setSizeEdit($(this));
});

// 중뒷품 수정량
$(document).on('blur', '#coatSizeEditWaistBack', function() {
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

    // 중동 수정량, 완성 계산
    setCoatWaist();

    // 중뒷품 수정량, 완성 계산
    setCoatWaistBack();
});

// 어깨, 소매좌, 소매우 수정량
$(document).on('blur', '#coatSizeEditShoulder, #coatSizeEditLeftSleeve, #coatSizeEditRightSleeve', function() {
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

    // 어깨 수정량, 완성 계산
    setSizeEdit($(this));
});

// 뒷기장 수정량
$(document).on('blur', '#coatSizeEditBackLength', function() {
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

    // 앞기장 수정량, 완성 계산
    setCoatFrontLength();

    // 뒷기장 수정량, 완성 계산
    setCoatBackLength();
});

// 굴신, 반신, 앞기장 변경 시
$(document).on('change', '#coatSizeFrontRound, #coatSizeBackRound, #coatSizeFrontLength', function() {
    setCoatFrontLength();
});

// 뒷중심 줄임 변경 시
$(document).on('change', '#coatSizeBackCenterShorten', function() {
    setCoatWaist();
    setCoatWaistBack();
});

// 우찌 변경 시
$(document).on('change', '#coatSizeChestLine', function() {
    setCoatChest();
    setCoatChestWidth();
    setCoatWaist();
    setCoatWaistFront();
});

// 목둘레 변경 시
$(document).on('change', '#coatSizeBackNeckline', function() {
    setCoatBackLength();
});
