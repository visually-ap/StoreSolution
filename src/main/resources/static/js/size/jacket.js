function initJacketGaugeResultSize() {
    $('#jacketSizeGaugeChest').val('');
    $('#jacketSizeGaugeChestWidth').val('');
    $('#jacketSizeGaugeUpperSideBody').val('');
    $('#jacketSizeGaugeBackWidth').val('');
    $('#jacketSizeGaugeWaist').val('');
    $('#jacketSizeGaugeWaistFront').val('');
    $('#jacketSizeGaugeMiddleSideBody').val('');
    $('#jacketSizeGaugeWaistBack').val('');
    $('#jacketSizeGaugeShoulder').val('');
    $('#jacketSizeGaugeLeftSleeve').val('');
    $('#jacketSizeGaugeRightSleeve').val('');
    $('#jacketSizeGaugeBackLength').val('');
    $('#jacketSizeGaugeFrontLength').val('');

    $('#jacketSizeResultChest').val('');
    $('#jacketSizeResultChestWidth').val('');
    $('#jacketSizeResultUpperSideBody').val('');
    $('#jacketSizeResultBackWidth').val('');
    $('#jacketSizeResultWaist').val('');
    $('#jacketSizeResultWaistFront').val('');
    $('#jacketSizeResultMiddleSideBody').val('');
    $('#jacketSizeResultWaistBack').val('');
    $('#jacketSizeResultShoulder').val('');
    $('#jacketSizeResultLeftSleeve').val('');
    $('#jacketSizeResultRightSleeve').val('');
    $('#jacketSizeResultBackLength').val('');
    $('#jacketSizeResultFrontLength').val('');
}

function setReadonlyJacketSizeEditInput(state) {
    $('.jacketSizeEditInput').prop('readonly', state);
    setReadonlySelect($('.jacketSizeEditSelect'), state);
}

function setJacketGaugeSize(data) {
    $('#jacketSizeGaugeChest').val(data.data01.toFixed(1));
    $('#jacketSizeGaugeChestWidth').val(data.data02.toFixed(1));
    $('#jacketSizeResultUpperSideBody').val(0.0.toFixed(1));
    $('#jacketSizeGaugeBackWidth').val(data.data03.toFixed(1));
    $('#jacketSizeGaugeWaist').val(data.data04.toFixed(1));
    $('#jacketSizeGaugeWaistFront').val(data.data05.toFixed(1));
    $('#jacketSizeResultMiddleSideBody').val(0.0.toFixed(1));
    $('#jacketSizeGaugeWaistBack').val(data.data06.toFixed(1));
    $('#jacketSizeGaugeShoulder').val(data.data07.toFixed(1));
    $('#jacketSizeGaugeLeftSleeve').val(data.data08.toFixed(1));
    $('#jacketSizeGaugeRightSleeve').val(data.data09.toFixed(1));
    $('#jacketSizeGaugeBackLength').val(data.data10.toFixed(1));
    $('#jacketSizeGaugeFrontLength').val(data.data11.toFixed(1));
}

function setJacketResultSize() {
    setJacketChest();
    setJacketChestWidth();
    setSizeEdit($('#jacketSizeEditUpperSideBody'));
    setSizeEdit($('#jacketSizeEditBackWidth'));
    setJacketWaist();
    setJacketWaistFront();
    setSizeEdit($('#jacketSizeEditMiddleSideBody'));
    setJacketWaistBack();
    setSizeEdit($('#jacketSizeEditShoulder'));
    setSizeEdit($('#jacketSizeEditLeftSleeve'));
    setSizeEdit($('#jacketSizeEditRightSleeve'));
    setJacketFrontLength();
    setJacketBackLength();
}

// 상동 계산
function setJacketChest() {
    let jacketSizeEditChestWidth = Number($('#jacketSizeEditChestWidth').val());
    let jacketSizeEditUpperSideBody = Number($('#jacketSizeEditUpperSideBody').val());
    let jacketSizeEditBackWidth = Number($('#jacketSizeEditBackWidth').val());
    let jacketSizeChestLine = Number($('#jacketSizeChestLine option:selected').val());

    // 수정값 계산
    let jacketSizeEditChest = jacketSizeEditChestWidth + jacketSizeEditUpperSideBody + jacketSizeEditBackWidth + jacketSizeChestLine;
    $('#jacketSizeEditChest').val(getDecimalPoint(jacketSizeEditChest, 1));

    // 상동 수정량 부호 설정
    setSign($('#jacketSizeEditChest'));

    // 완성값 계산
    setResultSize($('#jacketSizeEditChest'));
}

// 상앞품 계산
function setJacketChestWidth() {
    let jacketSizeGaugeChestWidth = Number($('#jacketSizeGaugeChestWidth').val());
    let jacketSizeEditChestWidth = Number($('#jacketSizeEditChestWidth').val());
    let jacketSizeChestLine = Number($('#jacketSizeChestLine option:selected').val());

    // 수정량 세팅
    $('#jacketSizeEditChestWidth').val(getDecimalPoint(jacketSizeEditChestWidth, 1));
    setSign($('#jacketSizeEditChestWidth'));

    // 완성값 계산 - 우찌때문에 공용 메소드 사용불가 -> 직접 계산
    let jacketSizeResultChestWidth = jacketSizeGaugeChestWidth + jacketSizeEditChestWidth + jacketSizeChestLine;
    $('#jacketSizeResultChestWidth').val(getDecimalPoint(jacketSizeResultChestWidth, 1));
}

// 중동 계산
function setJacketWaist() {
    let jacketSizeEditWaistFront = Number($('#jacketSizeEditWaistFront').val());
    let jacketSizeEditMiddleSideBody = Number($('#jacketSizeEditMiddleSideBody').val());
    let jacketSizeEditWaistBack = Number($('#jacketSizeEditWaistBack').val());
    let jacketSizeChestLine = Number($('#jacketSizeChestLine option:selected').val());
    let jacketSizeBackCenterShorten = Number($('#jacketSizeBackCenterShorten option:selected').val());

    // 수정값 계산
    let jacketSizeEditChest = jacketSizeEditWaistFront + jacketSizeEditMiddleSideBody + jacketSizeEditWaistBack + jacketSizeChestLine + jacketSizeBackCenterShorten;
    $('#jacketSizeEditWaist').val(getDecimalPoint(jacketSizeEditChest, 1));
    setSign($('#jacketSizeEditWaist'));

    // 완성값 세팅
    setResultSize($('#jacketSizeEditWaist'));
}

// 중앞품 계산
function setJacketWaistFront() {
    let jacketSizeGaugeWaistFront = Number($('#jacketSizeGaugeWaistFront').val());
    let jacketSizeEditWaistFront = Number($('#jacketSizeEditWaistFront').val());
    let jacketSizeChestLine = Number($('#jacketSizeChestLine option:selected').val());

    // 수정량 세팅
    $('#jacketSizeEditWaistFront').val(getDecimalPoint(jacketSizeEditWaistFront, 1));
    setSign($('#jacketSizeEditWaistFront'));

    // 완성값 계산 - 우찌때문에
    let jacketSizeResultWaistFront = jacketSizeGaugeWaistFront + jacketSizeEditWaistFront + jacketSizeChestLine;
    $('#jacketSizeResultWaistFront').val(getDecimalPoint(jacketSizeResultWaistFront, 1));
}

// 중뒷품 계산
function setJacketWaistBack() {
    let jacketSizeGaugeWaistBack = Number($('#jacketSizeGaugeWaistBack').val());
    let jacketSizeEditWaistBack = Number($('#jacketSizeEditWaistBack').val());
    let jacketSizeBackCenterShorten = Number($('#jacketSizeBackCenterShorten option:selected').val());

    // 수정량 세팅
    $('#jacketSizeEditWaistBack').val(getDecimalPoint(jacketSizeEditWaistBack, 1));
    setSign($('#jacketSizeEditWaistBack'));

    // 완성값 계산 - 뒷중심줄임때문에
    let jacketSizeResultWaistBack = jacketSizeGaugeWaistBack + jacketSizeEditWaistBack + jacketSizeBackCenterShorten;
    $('#jacketSizeResultWaistBack').val(getDecimalPoint(jacketSizeResultWaistBack, 1));
}

// 앞기장 계산
function setJacketFrontLength() {
    let jacketSizeFrontRound = Number($('#jacketSizeFrontRound option:selected').val());
    let jacketSizeBackRound = Number($('#jacketSizeBackRound option:selected').val());
    let jacketSizeFrontLength = Number($('#jacketSizeFrontLength option:selected').val());
    let jacketSizeEditBackLength = Number($('#jacketSizeEditBackLength').val());

    let resultEditValue = -(jacketSizeFrontRound * 2) + (jacketSizeBackRound * 2) + jacketSizeFrontLength + jacketSizeEditBackLength;

    // 수정량 세팅
    $('#jacketSizeEditFrontLength').val(getDecimalPoint(resultEditValue, 1));
    setSign($('#jacketSizeEditFrontLength'));

    // 완성값 세팅
    setResultSize($('#jacketSizeEditFrontLength'));
}

function setJacketBackLength() {
    let jacketSizeGaugeBackLength = Number($('#jacketSizeGaugeBackLength').val());
    let jacketSizeEditBackLength = Number($('#jacketSizeEditBackLength').val());
    let backNecklineEditValue = Number($('#jacketSizeBackNeckline option:selected').val());

    // 수정량 세팅
    $('#jacketSizeEditBackLength').val(getDecimalPoint(jacketSizeEditBackLength, 1));
    setSign($('#jacketSizeEditBackLength'));

    // 완성값 세팅 - 뒷중심줄임때문에
    let result = Number(jacketSizeGaugeBackLength) + Number(jacketSizeEditBackLength) + Number(backNecklineEditValue);
    $('#jacketSizeResultBackLength').val(getDecimalPoint(result, 1));
}

function initJacketGauge() {
    $("#jacketGauge").empty();
    $("#jacketGauge").append("<option value='' selected>게이지복</option>");
}

function initJacketPattern() {
    $('#jacketPattern').find('option:eq(0)').prop('selected', true);
}


let beforeJacketSize;
$(document).on('focus', '#jacketPattern', function () {
    beforeJacketSize = $("option:selected", this).val();
});
$(document).on('change', '#jacketPattern', function () {
    if (!isEmpty(beforeJacketSize) && !confirm('상의 패턴 변경 시 게이지복이 초기화됩니다.\n변경 하시겠습니까?')) {
        $('#jacketPattern').val(beforeJacketSize);
        return;
    }

    // 게이지복 초기화
    initJacketGauge();

    // 사이즈 입력 초기화
    initJacketGaugeResultSize();
    setReadonlyJacketSizeEditInput(true);

    if (isEmpty($(this).val())) {
        initJacketPattern();
        return;
    } else {
        // 게이지복 세팅
        callLazyService(
            "GET"
            , `/counseling/pattern/gauge?id=${$(this).val()}`
            , {}
            , function (response) {
                jacketGaugeList = response.list;
                $.each(jacketGaugeList, function (i, x) {
                    $("#jacketGauge").append(`<option value='${x.id}'>${x.sizeType}</option>`);
                });
            }
        );
    }
    $(this).blur();
});


// 사이즈 수정 이벤트들
$(document).on('focus', '.jacketSizeEditInput', function() {
    $(this).removeClass('positive_number_color');
    $(this).removeClass('negative_number_color');
    $(this).val($(this).val().replaceAll('+', ''));
    $(this).select();
});

// 상앞품 수정량
$(document).on('blur', '#jacketSizeEditChestWidth', function() {
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
    setJacketChest();

    // 상앞품 수정량, 완성 계산
    setJacketChestWidth();
});

// 상사이바, 상뒷품 수정량
$(document).on('blur', '#jacketSizeEditUpperSideBody, #jacketSizeEditBackWidth', function() {
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
    setJacketChest();

    // 상사이바 수정량, 완성 계산
    setSizeEdit($(this));
});

// 중앞품 수정량
$(document).on('blur', '#jacketSizeEditWaistFront', function() {
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
    setJacketWaist();

    // 중앞품 수정량, 완성 계산
    setJacketWaistFront();
});

// 중사이바 수정량
$(document).on('blur', '#jacketSizeEditMiddleSideBody', function() {
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
    setJacketWaist();

    // 중사이바 수정량, 완성 계산
    setSizeEdit($(this));
});

// 중뒷품 수정량
$(document).on('blur', '#jacketSizeEditWaistBack', function() {
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
    setJacketWaist();

    // 중뒷품 수정량, 완성 계산
    setJacketWaistBack();
});

// 어깨, 소매좌, 소매우 수정량
$(document).on('blur', '#jacketSizeEditShoulder, #jacketSizeEditLeftSleeve, #jacketSizeEditRightSleeve', function() {
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
$(document).on('blur', '#jacketSizeEditBackLength', function() {
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
    setJacketFrontLength();

    // 뒷기장 수정량, 완성 계산
    setJacketBackLength();
});

// 굴신, 반신, 앞기장 변경 시
$(document).on('change', '#jacketSizeFrontRound, #jacketSizeBackRound, #jacketSizeFrontLength', function() {
    setJacketFrontLength();
});

// 뒷중심 줄임 변경 시
$(document).on('change', '#jacketSizeBackCenterShorten', function() {
    setJacketWaist();
    setJacketWaistBack();
});

// 우찌 변경 시
$(document).on('change', '#jacketSizeChestLine', function() {
    setJacketChest();
    setJacketChestWidth();
    setJacketWaist();
    setJacketWaistFront();
});

// 목둘레 변경 시
$(document).on('change', '#jacketSizeBackNeckline', function() {
    setJacketBackLength();
});
